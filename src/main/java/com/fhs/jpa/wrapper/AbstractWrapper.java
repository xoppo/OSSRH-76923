package com.fhs.jpa.wrapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 抽象wrapper
 */
public class AbstractWrapper<T> {

    /**
     * 排序
     */
    protected List<OrderBy> orderByList = new ArrayList<>();

    /**
     * 临时过滤条件
     */
    protected List<TempPredicate> tempPredicates = new ArrayList<>();

    /**
     * 临时过滤条件-复杂的or的时候用的
     */
    protected List<List<TempPredicate>> orTempPredicates = new ArrayList<>();


    /**
     * 转换为JPA用的Specification对象
     *
     * @return
     */
    public Specification<T> build() {

        return (root, query, cb) -> {
            Predicate[] predicates = new Predicate[tempPredicates.size() + orTempPredicates.size()];
            int index = 0;
            for (int i = 0; i < tempPredicates.size(); ++i) {
                predicates[i] = toPredicate(root, query, cb, tempPredicates.get(i));
                index++;
            }
            //处理or的过滤条件
            for (List<TempPredicate> orTempPredicate : orTempPredicates) {
                Predicate[] oneOr = new Predicate[orTempPredicate.size()];
                for (int i = 0; i < orTempPredicate.size(); ++i) {
                    oneOr[i] = toPredicate(root, query, cb, orTempPredicate.get(i));
                }
                predicates[index] = cb.or(oneOr);
                index++;
            }
            if (Objects.equals(predicates.length, 0) && orderByList.isEmpty()) {
                return null;
                //如果没有order by 直接返回
            } else if (this.orderByList.isEmpty()) {
                return cb.and(predicates);
            } else {
                Order[] orders = new Order[this.orderByList.size()];
                for (int i = 0; i < orderByList.size(); i++) {
                    orders[i] = orderByList.get(i).isDesc()
                            ? cb.desc(root.get(orderByList.get(i).getProperty()))
                            : cb.asc(root.get(orderByList.get(i).getProperty()));
                }
                return query.orderBy(orders).where(predicates).getRestriction();
            }
        };
    }

    /**
     * TempPredicate 转换为 Predicate
     *
     * @param root
     * @param query
     * @param criteriaBuilder
     * @param predicate
     * @return
     */
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder, TempPredicate predicate) {
        switch (predicate.getOperator()) {
            case EQ:
                return criteriaBuilder.equal(root.get(predicate.getFieldName()), predicate.getValue());
            case NE:
                return criteriaBuilder.notEqual(root.get(predicate.getFieldName()), predicate.getValue());
            case GE:
                return criteriaBuilder.ge(root.get(predicate.getFieldName()), (Number) predicate.getValue());
            case GT:
                return criteriaBuilder.gt(root.get(predicate.getFieldName()), (Number) predicate.getValue());
            case LE:
                return criteriaBuilder.le(root.get(predicate.getFieldName()), (Number) predicate.getValue());
            case LT:
                return criteriaBuilder.lt(root.get(predicate.getFieldName()), (Number) predicate.getValue());
            case IN:
                CriteriaBuilder.In in = criteriaBuilder.in(root.get(predicate.getFieldName()));
                Object[] objects = (Object[]) predicate.getValue();
                for (Object obj : objects) {
                    in.value(obj);
                }
                return criteriaBuilder.and(in);
            case NOTIN:
                return criteriaBuilder.not(root.get(predicate.getFieldName()).in(predicate.getValue()));
            case LIKE:
                return criteriaBuilder.like(root.get(predicate.getFieldName()), "" + predicate.getValue());
            case ISNULL:
                return criteriaBuilder.isNull(root.get(predicate.getFieldName()));
            case NOTNULL:
                return criteriaBuilder.isNotNull(root.get(predicate.getFieldName()));
            case GREATERTHAN:
                return criteriaBuilder.greaterThan(root.get(predicate.getFieldName()).as(Date.class), (Date) predicate.getValue());
            case LESSTHAN:
                return criteriaBuilder.lessThan(root.get(predicate.getFieldName()).as(Date.class), (Date) predicate.getValue());
            default:
                return null;
        }
    }
}

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class OrderBy {
    /**
     * 属性
     */
    private String property;
    /**
     * 是否是desc
     */
    private boolean isDesc;
}

/**
 * 临时断言
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class TempPredicate {
    /**
     * 字段名
     */
    private String fieldName;
    /**
     * 操作符
     */
    private Operator operator;

    /**
     * 值
     */
    private Object value;
}

enum Operator {
    /**
     * 操作符枚举
     */
    EQ, LIKE, NE, GE, GT, LE, LT, IN, NOTLIKE, BETWEEN, NOTIN, ISNULL, NOTNULL, GREATERTHAN, LESSTHAN;
}

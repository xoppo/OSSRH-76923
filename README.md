# jpa-query-wrapper

#### 介绍
spring data jpa的LambdaQueryWrapper 实现，使用方法和mybatis plus保持一致，减少了mybatis plus开发者转向JPA的学习成本，仅仅实现了80%的功能，欢迎大家一起开发


#### 如何使用
1、DAO 继承JpaSpecificationExecutor

``` java
@Repository
public interface SchoolRepository extends JpaRepository<School, String>, JpaSpecificationExecutor<School> {

}
```
2、调用dao的findAll

``` java
@Autowired
    private SchoolRepository schoolRepository;

    @Test
    public void test(){
        System.out.println(schoolRepository.findAll(new LambdaQueryWrapper<School>().or(wrapper->{
            wrapper.eq(School::getId,1);//id为1 or id 为2 都被查出来
            wrapper.eq(School::getId,2);
        }).like(School::getSchoolName,"一") //名称like 一的
                .orderByAsc(School::getId).build()));//排序
    }
```
#### 支持的方法
 EQ, LIKE, NE, GE, GT, LE, LT, IN, BETWEEN, NOTIN,ISNULL,NOTNULL，Order by，OR
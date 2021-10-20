# jpa-query-wrapper

#### 介绍
spring data jpa的LambdaQueryWrapper 和QueryWrapper 实现，使用方法和mybatis plus保持一致，减少了mybatis plus开发者转向JPA的学习成本，仅仅实现了80%的功能，欢迎大家一起开发

#### MAVEN
```xml
<dependency>
  <groupId>com.fhs-opensource</groupId>
  <artifactId>jpa-query-wrapper</artifactId>
  <version>1.0.2</version>
</dependency>
```

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
3、高级用法   
 有的时候前端查询我们会封装一个类来接收前端参数，传统模式我们要把这个类转换为wrapper对象后查询，本插件提供了自动转换功能，demo如下：
 ``` java
 //pojo  主要是Wrapperble
 @Data
@Builder
@AllArgsConstructor
public class SchoolQueryPayload implements Wrapperble {

    @Eq
    private Integer id;

    @Like
    private String schoolName;

    //remark字段或者name字段like  value即可
    @GroupOrLike(fields = {"schoolName","remark"})
    private String remarkOrName;

}

  @Test
    public void testQueryWrapper(){
        /*
          1 SchoolQueryPayload模拟前端传过来的参数
          2 merge 一个LambdaQueryWrapper 模拟部分后端需要拼接的条件，比如租户id  用户id之类的
         */
        System.out.println(schoolRepository.findAll(SchoolQueryPayload.builder()
		.schoolName("一").remarkOrName("2").build().asWrapper()
                .merge(new LambdaQueryWrapper<School>().eq(School::getId,1)).build()));
    }
 ```
 4、SQL查询结果自动转换DTO   
 比如一个DTO是几个表联合查询或者使用聚合函数查询，使用原来的nativeSQL实现起来很恶心，要接收List<Object[]> 然后自己二次封装DTO，我们搞了个注解来简化这个问题
  ``` java
 

    @ResultClass(SchoolDto.class) //指定返回结果的Dto class
    @Query(value = "select id,school_name as schoolName,remark from school where id =?1",nativeQuery = true)
    SchoolDto findById(String id);

    @ResultClass(SchoolDto.class) //指定返回结果的Dto class
    @Query(value = "select id,school_name as schoolName,remark from school where id in (:ids)",nativeQuery = true)
    List<SchoolDto> findByIds(@Param("ids") Set<String> ids);


  ```
 5、 QueryWrapper   
 区别于LambdaQueryWrapper，QueryWrapper 支持接收po的属性名而不是属性的lambda表达式，用过mybatis plus的同学一定很熟悉，这里不多做说明了。
#### 支持的方法
 EQ, LIKE, NE, GE, GT, LE, LT, IN, BETWEEN, NOTIN,ISNULL,NOTNULL，Order by，OR
#### 联系我们
QQ群 976278956
package hellojpa;

import javax.persistence.*;
import java.util.*;

@Entity
public class Member extends BaseEntity{

    @Id
    private Long id;

    /**
     * @Column : 컬럼 매핑
     * - name : 필드와 매핑할 테이블 컬럼명 (default. 객체 필드명)
     * - insertable : 등록 가능 여부 (default. TRUE)
     * - updatable : 수정 가능 여부 (default. TRUE)
     *
     * 아래는 DDL 조건
     * - nullable : null 허용 여부 (default. TRUE)
     * - unique : 유니크 제약 조건, 제약조건명이 랜덤키로 생성되어 주로 사용하지는 않음 (default. FALSE)
     * - columnDefinition : 데이터베이스 컬럼 정보 직접 설정 (ex. varchar(100) default ‘EMPTY')
     * - length : 문자(String) 길이 제약조건 (default. 255)
     *
     * BigDecimal, BigInteger 타입에 사용
     * - precision : 소수점을 포함한 전체 자릿수 (default. 19)
     * - scale : 소수 자릿수 (default. 2)
     */
    @Column(name = "name")
    private String username;

    private Integer age;

    /**
     * @Enumerated : enum 타입 매핑
     * 주의. ORDINAL 사용 X! (enum 순서를 저장)
     */
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    /**
     * @Temporal : 날짜 타입 매핑
     * DATE, TIME, TIMESTAMP
     * (LocalDate, LocalDateTime 사용할 시 생략)
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    /**
     * @Lob : BLOB, CLOB 매핑
     * 매핑 필드 타입이 문자면 CLOB, 나머지는 BLOB으로 매핑
     * - CLOB: String, char[], java.sql.CLOB
     * • BLOB: byte[], java.sql. BLOB
     */
    @Lob
    private String description;

    /**
     * @Transient : 해당 필드를 컬럼에 매핑하지 않음
     * 메모리상에서만 임시로 데이터를 보관할 경우 사용
     */
    @Transient
    private String temp;

    @Embedded
    private Period workPeriod;
    @Embedded
    private Address homeAddress;

    @AttributeOverrides({
            @AttributeOverride(name = "city",
                    column = @Column(name = "work_city")),
            @AttributeOverride(name = "street",
                    column = @Column(name = "work_street")),
            @AttributeOverride(name = "zipcode",
                    column = @Column(name = "work_zipcode")),
    })
    private Address workAddress;

    @ElementCollection
    @CollectionTable(name = "FAVORITE_FOOD", joinColumns =
        @JoinColumn(name = "member_id")
    )
    @Column(name = "food_name")
    private Set<String> favoriteFoods = new HashSet<>();

    //    @ElementCollection
//    @CollectionTable(name = "ADDRESS", joinColumns =
//        @JoinColumn(name = "member_id")
//    )
//    private List<Address> addressHistory = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "member_id")
    private List<AddressEntity> addressHistory = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Integer getAge() {
        return age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public String getTemp() {
        return temp;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public void setWorkPeriod(Period workPeriod) {
        this.workPeriod = workPeriod;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public void setWorkAddress(Address workAddress) {
        this.workAddress = workAddress;
    }
}
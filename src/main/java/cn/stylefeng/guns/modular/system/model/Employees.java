package cn.stylefeng.guns.modular.system.model;

import java.math.BigDecimal;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaooo
 * @since 2019-06-21
 */
@TableName("employees")
public class Employees extends Model<Employees> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "emp_id",type = IdType.AUTO)
    private Integer empId;
    @TableField("emp_code")
    private String empCode;
    private String name;
    private String sex;
    private Integer age;
    private String nation;
    @TableField("ID_card")
    private String idCard;
    private BigDecimal salary;
    private String phone;
    @TableField("emergency_contact")
    private String emergencyContact;
    @TableField("emergency_contact_phone")
    private String emergencyContactPhone;
    @TableField("job_ID")
    private Integer jobId;
    private String description;
    @TableField("join_time")
    private Date joinTime;

    @JsonGetter("id")
    public Integer getEmpId() {
        return empId;
    }
    @JsonSetter("id")
    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyContactPhone() {
        return emergencyContactPhone;
    }

    public void setEmergencyContactPhone(String emergencyContactPhone) {
        this.emergencyContactPhone = emergencyContactPhone;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.empId;
    }

    @Override
    public String toString() {
        return "Employees{" +
        ", empId=" + empId +
        ", empCode=" + empCode +
        ", name=" + name +
        ", sex=" + sex +
        ", age=" + age +
        ", nation=" + nation +
        ", idCard=" + idCard +
        ", salary=" + salary +
        ", phone=" + phone +
        ", emergencyContact=" + emergencyContact +
        ", emergencyContactPhone=" + emergencyContactPhone +
        ", jobId=" + jobId +
        ", description=" + description +
        ", joinTime=" + joinTime +
        "}";
    }
}

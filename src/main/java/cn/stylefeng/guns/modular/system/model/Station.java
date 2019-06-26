package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaooo
 * @since 2019-06-21
 */
@TableName("station")
public class Station extends Model<Station> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "job_ID",type = IdType.AUTO)
    private Integer id;
    @TableField("job_code")
    private String jobCode;
    @TableField("job_name")
    private String jobName;
    @TableField("job_depart")
    private String jobDepart;
    @TableField("Immediate_superior")
    private String immediateSuperior;
    @TableField("job_category")
    private String jobCategory;
    @TableField("job_description")
    private String jobDescription;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobDepart() {
        return jobDepart;
    }

    public void setJobDepart(String jobDepart) {
        this.jobDepart = jobDepart;
    }

    public String getImmediateSuperior() {
        return immediateSuperior;
    }

    public void setImmediateSuperior(String immediateSuperior) {
        this.immediateSuperior = immediateSuperior;
    }

    public String getJobCategory() {
        return jobCategory;
    }

    public void setJobCategory(String jobCategory) {
        this.jobCategory = jobCategory;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Station{" +
        ", jobId=" + id +
        ", jobCode=" + jobCode +
        ", jobName=" + jobName +
        ", jobDepart=" + jobDepart +
        ", immediateSuperior=" + immediateSuperior +
        ", jobCategory=" + jobCategory +
        ", jobDescription=" + jobDescription +
        "}";
    }
}

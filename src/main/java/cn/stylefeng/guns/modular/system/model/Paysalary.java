package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaooo
 * @since 2019-06-21
 */
public class Paysalary extends Model<Paysalary> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;
    @TableField("salaried_people")
    private String salariedPeople;
    @TableField(value = "fixed_salary")
    private Double fixedSalary;
    @TableField("start_time")
    private String startTime;
    @TableField("end_time")
    private String endTime;

    @TableField(value = "selection_salary")
    private Double selectionSalary;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getSalariedPeople() {
        return salariedPeople;
    }

    public void setSalariedPeople(String salariedPeople) {
        this.salariedPeople = salariedPeople;
    }

    public Double getFixedSalary() {
        return fixedSalary;
    }

    public void setFixedSalary(Double fixedSalary) {
        this.fixedSalary = fixedSalary;
    }

    public Double getSelectionSalary() {
        return selectionSalary;
    }

    public void setSelectionSalary(Double selectionSalary) {
        this.selectionSalary = selectionSalary;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.Id;
    }

    @Override
    public String toString() {
        return "Paysalary{" +
                "Id=" + Id +
                ", salariedPeople='" + salariedPeople + '\'' +
                ", fixedSalary=" + fixedSalary +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", selectionSalary=" + selectionSalary +
                '}';
    }
}

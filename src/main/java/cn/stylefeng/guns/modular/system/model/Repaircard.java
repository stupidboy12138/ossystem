package cn.stylefeng.guns.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
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
public class Repaircard extends Model<Repaircard> {

    private static final long serialVersionUID = 1L;
    @TableId(value = "Id",type = IdType.AUTO)
    private Integer Id;
    @TableField("repair_man")
    private String repairMan;
    @TableField("repair_date")
    private String repairDate;
    @TableField("repair_reason")
    private String repairReason;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getRepairMan() {
        return repairMan;
    }

    public void setRepairMan(String repairMan) {
        this.repairMan = repairMan;
    }

    public String getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(String repairDate) {
        this.repairDate = repairDate;
    }

    public String getRepairReason() {
        return repairReason;
    }

    public void setRepairReason(String repairReason) {
        this.repairReason = repairReason;
    }

    @Override
    protected Serializable pkVal() {
        return this.Id;
    }

    @Override
    public String toString() {
        return "Repaircard{" +
        ", Id=" + Id +
        ", repairMan=" + repairMan +
        ", repairDate=" + repairDate +
        ", repairReason=" + repairReason +
        "}";
    }
}

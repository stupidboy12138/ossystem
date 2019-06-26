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
 * @author stylefeng
 * @since 2019-06-20
 */
public class Punchcard extends Model<Punchcard> {

    private static final long serialVersionUID = 1L;
    @TableId(value = "Id",type = IdType.AUTO)
    private Integer Id;
    @TableField("punch_man")
    private String punchMan;
    @TableField("punch_date")
    private Date punchDate;
    private String note;
    @TableField("clock_out")
    private Date clockOut;
    @TableField("duty_status")
    private String dutyStatus;
    @TableField("off_duty_status")
    private String offDutyStatus;
    @TableField("is_repair")
    private boolean isRepair;
    @TableField("is_leave")
    private boolean isLeave;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getPunchMan() {
        return punchMan;
    }

    public void setPunchMan(String punchMan) {
        this.punchMan = punchMan;
    }

    public Date getPunchDate() {
        return punchDate;
    }

    public void setPunchDate(Date punchDate) {
        this.punchDate = punchDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    protected Serializable pkVal() {
        return this.Id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Date getClockOut() {
        return clockOut;
    }

    public void setClockOut(Date clockOut) {
        this.clockOut = clockOut;
    }

    public String getDutyStatus() {
        return dutyStatus;
    }

    public void setDutyStatus(String dutyStatus) {
        this.dutyStatus = dutyStatus;
    }

    public String getOffDutyStatus() {
        return offDutyStatus;
    }

    public void setOffDutyStatus(String offDutyStatus) {
        this.offDutyStatus = offDutyStatus;
    }

    public boolean isRepair() {
        return isRepair;
    }

    public void setRepair(boolean repair) {
        isRepair = repair;
    }

    public boolean isLeave() {
        return isLeave;
    }

    public void setLeave(boolean leave) {
        isLeave = leave;
    }

    @Override
    public String toString() {
        return "Punchcard{" +
                "Id=" + Id +
                ", punchMan='" + punchMan + '\'' +
                ", punchDate=" + punchDate +
                ", note='" + note + '\'' +
                ", clockOut=" + clockOut +
                ", dutyStatus='" + dutyStatus + '\'' +
                ", offDutyStatus='" + offDutyStatus + '\'' +
                ", isRepair=" + isRepair +
                ", isLeave=" + isLeave +
                '}';
    }
}

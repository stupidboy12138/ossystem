package cn.stylefeng.guns.modular.system.model;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
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
@TableName("leaves")
public class Leave extends Model<Leave> {

    private static final long serialVersionUID = 1L;
    @TableId(value = "Id" ,type = IdType.AUTO)
    private Integer Id;
    @TableField("leave_man")
    private String leaveMan;
    @TableField("start_time")
    private String startTime;
    @TableField("end_time")
    private String endTime;
    private String reason;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getLeaveMan() {
        return leaveMan;
    }

    public void setLeaveMan(String leaveMan) {
        this.leaveMan = leaveMan;
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

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    protected Serializable pkVal() {
        return this.Id;
    }

    @Override
    public String toString() {
        return "Leave{" +
        ", Id=" + Id +
        ", leaveMan=" + leaveMan +
        ", startTime=" + startTime +
        ", endTime=" + endTime +
        ", reason=" + reason +
        "}";
    }
}

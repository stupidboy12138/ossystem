package cn.stylefeng.guns.modular.system.model;

import java.sql.Time;
import java.util.Date;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author xiaooo
 * @since 2019-06-21
 */
@TableName("classes")
public class Classes extends Model<Classes> {

    private static final long serialVersionUID = 1L;
    @TableId(value = "Id",type = IdType.AUTO)
    private Integer Id;
    private String code;
    private String endtime;
    private String note;
    private String name;
    private String begintime;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEndtime() {
        return endtime;
    }

    public void setEndtime(String endtime) {
        this.endtime = endtime;
    }

    public String getBegintime() {
        return begintime;
    }

    public void setBegintime(String begintime) {
        this.begintime = begintime;
    }

    @Override
    protected Serializable pkVal() {
        return this.Id;
    }

    @Override
    public String toString() {
        return "Classes{" +
        ", Id=" + Id +
        ", code=" + code +
        ", endtime=" + endtime +
        ", note=" + note +
        ", name=" + name +
        ", begintime=" + begintime +
        "}";
    }
}

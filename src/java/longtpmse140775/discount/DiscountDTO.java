/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longtpmse140775.discount;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author ACER
 */
public class DiscountDTO implements Serializable{

    private String codeId;
    private String codeName;
    private int discountPercent;
    private Date dateCreate;
    private Date dateExpire;

    public DiscountDTO() {
    }

    public DiscountDTO(String codeId, String codeName, int discountPercent, Date dateCreate, Date dateExpire) {
        this.codeId = codeId;
        this.codeName = codeName;
        this.discountPercent = discountPercent;
        this.dateCreate = dateCreate;
        this.dateExpire = dateExpire;
    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public int getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(int discountPercent) {
        this.discountPercent = discountPercent;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateExpire() {
        return dateExpire;
    }

    public void setDateExpire(Date dateExpire) {
        this.dateExpire = dateExpire;
    }

}

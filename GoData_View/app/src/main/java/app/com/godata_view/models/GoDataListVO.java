package app.com.godata_view.models;

import java.io.Serializable;

/*
 공공DB중 중앙복지서비스의 리스트 데이터를 담을 VO

 초기 안드로이드에서는 VO를 만들때 Serializable을 implemnets를 했었는데,
 최근에 안드로이드 폰들의 성능이 좋아지면서 생략을 많이 했다.

 그런데, 문제가 Activity 간에 Intent를 만들고 VO를 ArrayList에 담아서
 전달하다보니, 다시 문제가 생겼다.
 그래서 Activity간에 VO를 전달 할때는 Serializable을 imp 하자
 */
public class GoDataListVO implements Serializable{

    private String servId;
    private String servNm;
    private String jurMnofNm;
    private String jurOrgNm;
    private String inqNum;
    private String servDgst;
    private String servDtlLink;
    private String svcfrstRegTs;

    public GoDataListVO() {
    }

    public GoDataListVO(String servId, String servNm, String jurMnofNm, String jurOrgNm, String inqNum, String servDgst, String servDtlLink, String svcfrstRegTs) {
        this.servId = servId;
        this.servNm = servNm;
        this.jurMnofNm = jurMnofNm;
        this.jurOrgNm = jurOrgNm;
        this.inqNum = inqNum;
        this.servDgst = servDgst;
        this.servDtlLink = servDtlLink;
        this.svcfrstRegTs = svcfrstRegTs;
    }

    public String getServId() {
        return servId;
    }

    public void setServId(String servId) {
        this.servId = servId;
    }

    public String getServNm() {
        return servNm;
    }

    public void setServNm(String servNm) {
        this.servNm = servNm;
    }

    public String getJurMnofNm() {
        return jurMnofNm;
    }

    public void setJurMnofNm(String jurMnofNm) {
        this.jurMnofNm = jurMnofNm;
    }

    public String getJurOrgNm() {
        return jurOrgNm;
    }

    public void setJurOrgNm(String jurOrgNm) {
        this.jurOrgNm = jurOrgNm;
    }

    public String getInqNum() {
        return inqNum;
    }

    public void setInqNum(String inqNum) {
        this.inqNum = inqNum;
    }

    public String getServDgst() {
        return servDgst;
    }

    public void setServDgst(String servDgst) {
        this.servDgst = servDgst;
    }

    public String getServDtlLink() {
        return servDtlLink;
    }

    public void setServDtlLink(String servDtlLink) {
        this.servDtlLink = servDtlLink;
    }

    public String getSvcfrstRegTs() {
        return svcfrstRegTs;
    }

    public void setSvcfrstRegTs(String svcfrstRegTs) {
        this.svcfrstRegTs = svcfrstRegTs;
    }

    @Override
    public String toString() {
        return "GoDataListVO{" +
                "servId='" + servId + '\'' +
                ", servNm='" + servNm + '\'' +
                ", jurMnofNm='" + jurMnofNm + '\'' +
                ", jurOrgNm='" + jurOrgNm + '\'' +
                ", inqNum='" + inqNum + '\'' +
                ", servDgst='" + servDgst + '\'' +
                ", servDtlLink='" + servDtlLink + '\'' +
                ", svcfrstRegTs='" + svcfrstRegTs + '\'' +
                '}';
    }
}

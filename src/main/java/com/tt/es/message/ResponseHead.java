package com.tt.es.message;

import com.tt.es.constant.RetCodeConstant;

public class ResponseHead {

    private static final String status = "0";

    private static final String code = "0";

    private static final String desc = "success";

    private String respStatus;// 0，成功；1，失败

    private String respCode;// 响应码，成功时返回000000000000；失败时返回错误码

    private String respDesc;// 描述信息，成功时返回success；失败时返回错误信息

    private String requestId;

    // ..other field

    public ResponseHead() {
        this.respStatus = ResponseHead.status;
        this.respCode = ResponseHead.code;
        this.respDesc = ResponseHead.desc;
    }

    public ResponseHead(String respStatus, String respCode, String respDesc) {
        this.respStatus = respStatus;
        this.respCode = respCode;
        this.respDesc = respDesc;
    }


    public static ResponseHead buildFailedHead() {
        return new ResponseHead("1", RetCodeConstant.ERROR, "failure");
    }

    public static ResponseHead buildFailedHead(String str) {
        return new ResponseHead("1", RetCodeConstant.ERROR, str);
    }

    public static ResponseHead buildSuccessHead() {
        return new ResponseHead("0", RetCodeConstant.SUCCESS, "success");
    }

    public static ResponseHead buildNoDataHead() {
        return new ResponseHead("0",  RetCodeConstant.SUCCESS, "数据不存在");
    }

    public String getRespStatus() {
        return this.respStatus;
    }

    public void setRespStatus(String respStatus) {
        this.respStatus = respStatus;
    }

    public String getRespCode() {
        return this.respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespDesc() {
        return this.respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }



    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResponseHead{");
        sb.append("respStatus='").append(this.respStatus).append('\'');
        sb.append(", respCode='").append(this.respCode).append('\'');
        sb.append(", respDesc='").append(this.respDesc).append('\'');
        sb.append(", requestId='").append(this.requestId).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

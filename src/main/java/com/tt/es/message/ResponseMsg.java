package com.tt.es.message;


public class ResponseMsg<T> {

    private ResponseHead head;
    
    private T data;

    public ResponseMsg(ResponseHead head, T data) {
        this.head = head;
        this.data = data;
    }

    public ResponseMsg(T data) {
        this();
        this.data = data;
    }

    public ResponseMsg() {
        this.head = new ResponseHead();
    }

    public ResponseHead getHead() {
        return this.head;
    }

    public void setHead(ResponseHead head) {
        this.head = head;
    }

    public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ResponseMsg{");
        sb.append("head=").append(this.getHead());
        sb.append(", data=").append(this.getData());
        sb.append('}');
        return sb.toString();
    }
}

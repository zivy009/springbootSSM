package com.zivy009.demo.springbootshirodwz.common.constants;


/**
 *  
 * code  码表
 
 */
public enum MyCodeOfException {
    //--------------------------成功----------------------------------------
    SUCCESS(2000,"成功"),
    //--------------------------服务端导致的----------------------------------------
    ERROR(5000,"发生错误"),
	UNKNOWN_STATUS(5001,"未知"),
	WARN_MSG(5002,""),// 不需要code码的，各种信息提示用这个
//-------------------------------客户端导致的警告-----------------------------------
	ERROR_CLIENT(4000,"请求异常"),
	PARAMETER_NULL(4001,"参数为空"),
	REPEAT_ADD(4002,"重复添加"),
	ERROR_TOKEN(4010,"token失效"),
	APP_UPGRADE(4020,"请升级到最新版本"),
	ERROR_PWD(4030,"密码错误"),
	SENSITIVE_WORD(4040,"存在敏感词!"),
	FORBIDDEN_ACCESS(4050,"您的访问太频繁，请稍后再试")
	;		
	public Number code;
	
	public String des;
	private MyCodeOfException(Number code,String des){
		this.code = code;
		this.des = des;
	}
	public String getCodeString(){
	    
		return code.toString();
	}
	public static MyCodeOfException get(Number code){
		for(MyCodeOfException errorCode:MyCodeOfException.values()){
			if(errorCode.code.toString().equals(code.toString())){
				return errorCode;
			}
		}
		return null;
	}
	
	@Override
	public String toString(){
		return "code:"+code +", des:"+des;
	}
	
	public static void main(String[] args) {
		MyCodeOfException errorCode = get(200);
		System.out.println(MyCodeOfException.SUCCESS.des);
		if(null != errorCode)
			System.out.println(errorCode);
			System.err.println(errorCode.code+"<==========>"+errorCode.des);
	}
}

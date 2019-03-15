package commonUtil;
/**
 * 根据交接人获取下一节审批人点信息
 * @author Administrator
 *
 */
public class UserOAMessage {
	public static String getUserId(String HandoverPerson){
		String UserId="";
		if("郑彩飞".equals(HandoverPerson)){
			UserId="tzzcf";
		}
		if("元幼幼".equals(HandoverPerson)){
			UserId="tzyuanyy";
		}
		if("徐海贞".equals(HandoverPerson)){
			UserId="tzxhz";
		}
		if("郑晓娜".equals(HandoverPerson)){
			UserId="zhengxiaona2";
		}
		
		return UserId;
	}
	public static String getUserName(String HandoverPerson){
		String UserName="";
		if("郑彩飞".equals(HandoverPerson)){
			UserName="郑彩飞";
		}
		if("元幼幼".equals(HandoverPerson)){
			UserName="元幼幼";
		}
		if("徐海贞".equals(HandoverPerson)){
			UserName="徐海贞";
		}
		if("郑晓娜".equals(HandoverPerson)){
			UserName="郑晓娜";
		}
		return UserName;
	}
	public static String getParttimerole(String HandoverPerson){
		String Parttimerole="";
		/*if("郑彩飞".equals(HandoverPerson)){
			Parttimerole="6cd9d3b9-ddba-4a43-a195-5b216d6cfb3c#c8527b7b-83b5-4f4e-9ee4-9b5566973ffd#818ba12c8aa#-1#45a53cab-fae0-4b9c-b4b3-5b7c65565618#tzzcf";
		}
		if("元幼幼".equals(HandoverPerson)){
			Parttimerole="b17e347d-1791-4735-a8a4-d389e10ea14a#35b45481-f9c9-4662-9839-83e7747dc3bc#818ba12c8aa#-1#45a53cab-fae0-4b9c-b4b3-5b7c65565618#tzyuanyy";
		}
		if("徐海贞".equals(HandoverPerson)){
			Parttimerole="8ea96b29-7ace-44b7-b817-e274cb2b9873#65674e20-7602-4f77-8c78-6002a071da8c#818ba12c8aa#-1#45a53cab-fae0-4b9c-b4b3-5b7c65565618#tzxhz";
		}
		if("郑晓娜".equals(HandoverPerson)){
			Parttimerole="c93d3be8-fac1-43c1-8a82-0b6f62e00e58#ec3f39a3-5af8-48ca-9e59-4315b2ed0343#d6efb5aedda#-1#4eb67dae-5464-4ddf-9ed4-1e0d1acae46b#zhengxiaona2";
		}*/
		if("郑彩飞".equals(HandoverPerson)){
			Parttimerole="1b35e843-af16-4d9e-9215-8b4028c593ca#f193387b-468a-4dc8-aa44-f70c29d038cd#818ba12c8aa#-1#fee08cb9-ab15-436f-9554-8ef8a2d5d3de#tzzcf";
		}
		if("元幼幼".equals(HandoverPerson)){
			Parttimerole="231f32ec-5a44-4472-8e8d-119bdbdee573#1ea85a17-87b8-4432-953b-5ab3c97c182f#818ba12c8aa#-1#fee08cb9-ab15-436f-9554-8ef8a2d5d3de#tzyuanyy";
		}
		if("徐海贞".equals(HandoverPerson)){
			Parttimerole="0bdfeaea-c9f2-4686-8670-9218fa8c4158#d8e73234-5fcb-4c66-9edd-cf23100b42f9#818ba12c8aa#-1#fee08cb9-ab15-436f-9554-8ef8a2d5d3de#tzxhz";
		}
		if("郑晓娜".equals(HandoverPerson)){
			Parttimerole="d6601e79-08e1-43e7-9834-9e85e75c17be#2d211078-9624-4203-a60c-fcd557f7c7c5#d6efb5aedda#-1#0d28afbe-a0f5-468f-83f2-885d348e9319#zhengxiaona2";
		}
		if("李学明".equals(HandoverPerson)){
			Parttimerole="9b5a6c42-4b21-49a4-97d8-9b32b860b322#511a6c84-2d4f-4fe1-bee1-4b3d0e43d9e0#83007f29eb8#-1#6deca2d7-f78c-4c82-b60c-58c3f52133b9#lixuem";
		}
		return Parttimerole;
	}
}

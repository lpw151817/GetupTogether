package cm.getuptogether.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.renn.rennsdk.RennResponse;

/**
 * json format
 * {
	"response": {
		"name": "刘沛文",
		"id": 737553323,
		"avatar": [
			{
				"size": "TINY",
				"url": "http://hdn.xnimg.cn/photos/hdn321/20140203/1720/tiny_f04f_1332000485d8113e.jpg"
			},
			{
				"size": "HEAD",
				"url": "http://hdn.xnimg.cn/photos/hdn221/20140203/1720/h_head_Pi28_59c3000080b1113e.jpg"
			},
			{
				"size": "MAIN",
				"url": "http://hdn.xnimg.cn/photos/hdn221/20140203/1720/h_main_dLQ9_59c3000080b1113e.jpg"
			},
			{
				"size": "LARGE",
				"url": "http://hdn.xnimg.cn/photos/hdn221/20140203/1720/h_large_u7Mb_59c3000080b1113e.jpg"
			}
		],
		"star": 1,
		"basicInformation": null,
		"education": null,
		"work": null,
		"like": null,
		"emotionalState": null
	}
}
 * @author Jerry
 *
 */


public class RenrenUtils {
	private static JSONObject rennResponse2JsonObject(String re) {
		try {
			re = re.substring(re.indexOf("{"), re.length() - 1);
			return new JSONObject(re);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String getUsername(String response) {
		try {
			return rennResponse2JsonObject(response).getJSONObject("response").getString("name");
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取TINY头像
	 * @param response
	 * @return
	 */
	public static String getLogoURL(String response) {
		try {
			JSONArray array = rennResponse2JsonObject(response).getJSONObject("response").getJSONArray("avatar");
			return array.getJSONObject(0).getString("url");
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}

	}
}

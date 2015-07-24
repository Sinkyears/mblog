/**
 * 
 */
package mblog.web.controller.api;

import java.util.List;

import mtons.modules.pojos.UserProfile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import mblog.commons.data.Post;
import mblog.commons.data.Tag;
import mblog.core.planet.PostPlanet;
import mblog.core.planet.TagPlanet;
import mblog.web.controller.BaseController;

/**
 * 侧边栏数据加载
 * 
 * @author langhsu
 *
 */
@Controller
@RequestMapping("/api")
public class SidebarController extends BaseController {
	@Autowired
	private PostPlanet postPlanet;
	@Autowired
	private TagPlanet tagPlanet;
	
	@RequestMapping("/recents.json")
	public @ResponseBody List<Post> recent() {
		UserProfile up = getSubject().getProfile();
		long ignoreUserId = 0;
		if (up != null) {
			ignoreUserId = up.getId();
		}
		List<Post> rets = postPlanet.findRecents(8, ignoreUserId);
		return rets;
	}
	
	@RequestMapping("/hots.json")
	public @ResponseBody List<Post> hots() {
		UserProfile up = getSubject().getProfile();
		long ignoreUserId = 0;
		if (up != null) {
			ignoreUserId = up.getId();
		}
		List<Post> rets = postPlanet.findHots(8, ignoreUserId);
		return rets;
	}
	
	@RequestMapping("/hot_tags.json")
	public @ResponseBody List<Tag> hotTags() {
		List<Tag> rets = tagPlanet.topTags(12, false);
		return rets;
	}
	
}

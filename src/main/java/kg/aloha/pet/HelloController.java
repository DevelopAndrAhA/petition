package kg.aloha.pet;

import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import kg.aloha.pet.model.*;
import kg.aloha.pet.model.model.Aktivist;
import kg.aloha.pet.model.model.Profession;
import kg.aloha.pet.model.model.Profession_Count;
import kg.aloha.pet.service.MyService;
import kg.aloha.pet.service.OkHttpUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/")
public class HelloController {
	@Autowired
	MyService service;

	@ResponseBody
	@RequestMapping(method = RequestMethod.GET,produces = "text/html; charset=utf-8")
	public String printWelcome() {
		OkHttpClient client = new OkHttpUtils().getInstance();


		//com.squareup.okhttp.RequestBody body = com.squareup.okhttp.RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonToSend);
        Request request = new Request.Builder()
                .url("http://sasisa.ru/")
						//.post(body)
                .build();
        try{
            Response response = client.newCall(request).execute();
            String s = response.body().string();
            System.out.println(s);
			return s;
        }catch (Exception e){e.printStackTrace();}

		return "hello";
		//return "Peer";
	}

	@ResponseBody
	@RequestMapping(value = "upload-json-conf",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public String uploadJsonConf(@RequestParam("file") MultipartFile json,@RequestParam("frakciya") String frakciya_name) throws IOException {

		Frakciya frakciya = new Frakciya();
		frakciya.setName(frakciya_name);

		frakciya = service.save(frakciya);

		String content = new String(json.getBytes(), StandardCharsets.UTF_8);
		JSONArray jsonArray = new JSONArray(content);
		ArrayList<Deputat> arrayList = new ArrayList<Deputat>();

		for(int i=0;i<jsonArray.length();i++){
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			Deputat deputat = new Deputat();
			deputat.setFio(jsonObject.getString("fio"));
			deputat.setPhoto(jsonObject.getString("srcImage"));
			deputat.setInfo(jsonObject.getString("info"));
			deputat.setF_id(frakciya.getF_id());
			arrayList.add(deputat);
		}
		boolean res = service.save(arrayList);
		if(res){
			return content;
		}else{
			return null;
		}

	}

	@ResponseBody
	@RequestMapping(value = "getalldep",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	public Object getAllDeputat(){
		List list = service.getAllDep();
		if(list!=null){
			if(list.size()!=0){
				return list;
			}
		}
		return null;
	}
	@ResponseBody
	@RequestMapping(value = "getAllPrezident",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	public Object getAllPrezident(){
		List list = service.getAllPrezident();
		if(list!=null){
			if(list.size()!=0){
				return list;
			}
		}
		return null;
	}
	@ResponseBody
	@RequestMapping(value = "getGolosPrezident",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	public Object getGolosPrezident(){
		List list = service.getGolos4Prezident();
		if(list!=null){
			if(list.size()!=0){
				return list;
			}
		}
		return null;
	}
	@ResponseBody
	@RequestMapping(value = "getAllAktivist",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	public Object getAllAktivist(){
		List list = service.getAllAktivist();
		if(list!=null){
			if(list.size()!=0){
				return list;
			}
		}
		return null;
	}




	@ResponseBody
	@RequestMapping(value = "getCommentsAct",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	public Object getCommentsAct(@RequestParam("a_id")long a_id,@RequestParam("u_id")long u_id){
		List list = service.getMyCommentsToAct(a_id, u_id);
		List list2 = service.getAllCommentsToAct(a_id);

		List conatiner = new ArrayList<>();
		if(list!=null){
			if(list.size()!=0){
				conatiner.add(list);
			}
		}
		if(list2!=null){
			if(list2.size()!=0){
				conatiner.add(list2);
			}
		}
		if(conatiner.size()!=0){
			return conatiner;
		}
		return null;
	}


	@ResponseBody
	@RequestMapping(value = "getCommentsDep",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	public Object getCommentsDep(@RequestParam("d_id")long d_id,@RequestParam("u_id")long u_id){
		List list = service.getMyCommentsToDep(d_id,u_id);
		List list2 = service.getAllCommentsToDep(d_id);

		List conatiner = new ArrayList<>();
		if(list!=null){
			if(list.size()!=0){
				conatiner.add(list);
			}
		}
		if(list2!=null){
			if(list2.size()!=0){
				conatiner.add(list2);
			}
		}
		if(conatiner.size()!=0){
			return conatiner;
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "getCommentsPrezident",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	public Object getCommentsPrezident(@RequestParam("p_id")long p_id,@RequestParam("u_id")long u_id){
		List list = service.getMyCommentsToPrezident(p_id, u_id);
		List list2 = service.getAllCommentsToPrezident(p_id);

		List conatiner = new ArrayList<>();
		if(list!=null){
			if(list.size()!=0){
				conatiner.add(list);
			}
		}
		if(list2!=null){
			if(list2.size()!=0){
				conatiner.add(list2);
			}
		}
		if(conatiner.size()!=0){
			return conatiner;
		}
		return null;
	}

	@ResponseBody
	@RequestMapping(value = "getCountUsers",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	public Object getCountUsers(){
		int countUsers = service.getCountUsers();
		String countUSersStr = "{\"countUsers\":"+countUsers+"}";
		return countUSersStr;
	}




	@ResponseBody
	@RequestMapping(value = "saveComment",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Object saveComment(@RequestBody Comment comment){
		service.save(comment);
		return comment;
	}

	@ResponseBody
	@RequestMapping(value = "saveAktivist",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Object saveActivist(@RequestBody Aktivist aktivist){
		aktivist  = service.save(aktivist);
		return aktivist;
	}
	@ResponseBody
	@RequestMapping(value = "saveProfession",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Object saveProfession(@RequestBody  Profession profession){
		profession  = service.save(profession);
		return profession;
	}
	@ResponseBody
	@RequestMapping(value = "saveProfession_Count",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Object saveProfession_Count(@RequestBody  Profession_Count profession_count){
		profession_count  = service.save(profession_count);
		return profession_count;
	}
	@ResponseBody
	@RequestMapping(value = "savePrezident",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Object savePrezident(@RequestBody List<Prezident> prezidents){
		service.save(prezidents);
		return "{\"save\":\"success\"}";
	}


	@ResponseBody
	@RequestMapping(value = "saveGolosAct",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Object saveGolos(@RequestBody Golos golos){
		golos  = service.saveGolosAct(golos);
		return golos;
	}

	@ResponseBody
	@RequestMapping(value = "saveGolosDep",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Object saveGolosDep(@RequestBody Golos golos){
		golos  = service.saveGolosDep(golos);
		return golos;
	}



	@ResponseBody
	@RequestMapping(value = "saveGolosPrezident",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Object saveGolosPrezident(@RequestBody Golos golos){
		service.deleteGolos(golos.getG_id());
		golos  = service.saveGolosPrezident(golos);
		return golos;
	}




	@ResponseBody
	@RequestMapping(value = "saveAddress",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Object saveAddress(@RequestBody Address address){
		address  = service.save(address);
		return address;
	}
	@ResponseBody
	@RequestMapping(value = "saveFamily",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Object saveFamily(@RequestBody Family family){
		family  = service.save(family);
		return family;
	}
	@ResponseBody
	@RequestMapping(value = "saveImushestvo",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Object saveImushestvo(@RequestBody Imushestvo imushestvo) {
		imushestvo = service.save(imushestvo);
		return imushestvo;
	}




//    ============================================================
//    ============================================================
//    ============================================================




	@ResponseBody
	@RequestMapping(value = "search",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	public Object searchDeputat(@RequestParam("fio")String fio){
		JSONArray jsonArray = new JSONArray();
		List list = service.searchDep(fio);
		if(list!=null){
			if(list.size()!=0){
				for(int i=0;i<list.size();i++){
					Deputat deputat = (Deputat)list.get(i);
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("info",deputat.getInfo());
					jsonObject.put("fio",deputat.getFio());
					jsonObject.put("d_id",deputat.getD_id());
					jsonObject.put("f_id",deputat.getF_id());
					jsonObject.put("img",deputat.getPhoto());
					jsonArray.put(i,jsonObject);
				}

			}
		}
		return jsonArray.toString();
	}

}
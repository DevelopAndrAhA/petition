package kg.aloha.pet;

import kg.aloha.pet.model.*;
import kg.aloha.pet.model.model.Aktivist;
import kg.aloha.pet.model.model.Profession;
import kg.aloha.pet.model.model.Profession_Count;
import kg.aloha.pet.service.MyService;
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


	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
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

	@ResponseBody
	@RequestMapping(value = "getRateAct",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	public Object getRatingAct(@RequestParam("id") long id){
		String str = service.getGolos4Activist(id)+"";//количество плюсовых голосов
		String str2 = service.getGolosMinus4Activist(id) + "";//количество минусовых голосов
		return "{\"plus\":\""+str+"\",\"minus\":\""+str2+"\"}";
	}

	@ResponseBody
	@RequestMapping(value = "getRateDep",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	public Object getRatingDep(@RequestParam("id") long id){
		String str = service.getGolos4Deputat(id)+"";//количество плюсовых голосов
		String str2 = service.getGolosMinus4Deputat(id)+"";//количество минусовых голосов
		return "{\"plus\":\""+str+"\",\"minus\":\""+str2+"\"}";
	}

	@ResponseBody
	@RequestMapping(value = "getRatePrezident",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
	public Object getRatingPrezident(@RequestParam("id")long id){
		String str = service.getGolos4Prezident(id)+"";//количество плюсовых голосов
		String str2 = service.getGolosMinus4Prezident(id)+"";//количество минусовых голосов
		return "{\"plus\":\""+str+"\",\"minus\":\""+str2+"\"}";
	}


	@ResponseBody
	@RequestMapping(value = "getDepCommentsAct",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
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
	@RequestMapping(value = "getDepCommentsDep",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
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
	@RequestMapping(value = "getDepCommentsPrezident",method = RequestMethod.GET,produces = "application/json; charset=utf-8")
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
	public Object saveActivist(Aktivist aktivist){
		aktivist  = service.save(aktivist);
		return aktivist;
	}
	@ResponseBody
	@RequestMapping(value = "saveProfession",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Object saveProfession(Profession profession){
		profession  = service.save(profession);
		return profession;
	}
	@ResponseBody
	@RequestMapping(value = "saveProfession_Count",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Object saveProfession_Count(Profession_Count profession_count){
		profession_count  = service.save(profession_count);
		return profession_count;
	}
	@ResponseBody
	@RequestMapping(value = "savePrezident",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Object savePrezident(Prezident prezident){
		prezident  = service.save(prezident);
		return prezident;
	}

	@ResponseBody
	@RequestMapping(value = "saveGolosAct",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Object saveGolos(Golos golos,Golos_minus golos_minus){
		golos  = service.saveGolosAct(golos, golos_minus);
		return golos;
	}

	@ResponseBody
	@RequestMapping(value = "saveGolos_minusAct",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Object saveGolos_minus(Golos_minus golos_minus,Golos golos){
		golos_minus  = service.saveGolosMinusAct(golos_minus, golos);
		return golos_minus;
	}

	@ResponseBody
	@RequestMapping(value = "saveGolosDep",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Object saveGolosDep(Golos golos,Golos_minus golos_minus){
		golos  = service.saveGolosDep(golos, golos_minus);
		return golos;
	}

	@ResponseBody
	@RequestMapping(value = "saveGolos_minusDep",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Object saveGolos_minusDep(Golos_minus golos_minus,Golos golos){
		golos_minus  = service.saveGolosMinusDep(golos_minus, golos);
		return golos_minus;
	}

	@ResponseBody
	@RequestMapping(value = "saveGolosPrezident",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Object saveGolosPrezident(Golos golos,Golos_minus golos_minus){
		golos  = service.saveGolosPrezident(golos, golos_minus);
		return golos;
	}

	@ResponseBody
	@RequestMapping(value = "saveGolos_minusPrezident",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Object saveGolos_minusPrezident(Golos_minus golos_minus,Golos golos){
		golos_minus  = service.saveGolosMinusPrezident(golos_minus, golos);
		return golos_minus;
	}



	@ResponseBody
	@RequestMapping(value = "saveAddress",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Object saveAddress(Address address){
		address  = service.save(address);
		return address;
	}
	@ResponseBody
	@RequestMapping(value = "saveFamily",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Object saveFamily(Family family){
		family  = service.save(family);
		return family;
	}
	@ResponseBody
	@RequestMapping(value = "saveImushestvo",method = RequestMethod.POST,produces = "application/json; charset=utf-8")
	public Object saveImushestvo(Imushestvo imushestvo){
		imushestvo  = service.save(imushestvo);
		return imushestvo;
	}
}
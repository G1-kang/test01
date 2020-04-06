package com.test.dashboard.common.crawling;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.test.dashboard.model.dto.CrawlingDto;


public class crawling {
	
	@Autowired private CrawlingDto dto;

	public List<CrawlingDto> crwlparser(String keyword) {

		List<CrawlingDto> list = new ArrayList<CrawlingDto>();
		
		try {
			System.out.println("==============================>>>>>>>>>>>>>>>>>>>>>>>>>"+keyword);
			Document doc = Jsoup
					.connect("https://search.naver.com/search.naver?where=realtime&sm=tab_jum&query=" + keyword).get();
			Elements contents = doc.select("ul.type01>li");

			for (int i = 0; i < contents.size(); i++) {
				
				// li 안에 내용이 담겨있는 부분 
				Element common = contents.get(i).child(1);
				
				// 주소 스플릿 
				String[] link = common.child(0).select("a").attr("href").split("/");
				
				// 어디서 왔는지 구분 
				String[] out = link[2].trim().split("\\.");
				String from = "";
				dto = new CrawlingDto();
			
				
				for (String rs : out) {
					
					if (rs.equals("naver")) {
						dto.setWcrwlwriter(common.select("span.user_name").text());
						dto.setWcrwlcontent(common.select("a.txt_link").html());
						dto.setWcrwldate(common.select("span.sub_time").text());
						dto.setWcrwllink(contents.get(i).child(0).child(0).attr("href"));
						from = "naver";

					}else if (rs.equals("twitter")) {
						dto.setWcrwlwriter(common.select("span.user_name").text()+"\t"+common.select("span.user_id").text());
						dto.setWcrwlcontent(common.select("span._twitter").html());
						dto.setWcrwldate(common.select("a._timeinfo").text());
						dto.setWcrwllink(common.child(1).select("span").attr("data-src"));
						from = "twitter";
						
					} else if (rs.equals("vlive")) {
						dto.setWcrwlwriter(common.select("span.user_name").text()+"\t"+common.select("span.user_id"));
						dto.setWcrwlcontent(common.select("a.txt_link").html());
						dto.setWcrwldate(common.select("span.sub_time").text());
						dto.setWcrwllink(contents.get(i).child(0).child(0).attr("href"));
						from ="vlive";
					}
				}
			
				dto.setWcrwlfrom(from);
				
				list.add(dto);
			}
			return list;
		} catch (IOException e) {
			System.out.println("Some Error");
			e.printStackTrace();
		}
		return list;

	}
}

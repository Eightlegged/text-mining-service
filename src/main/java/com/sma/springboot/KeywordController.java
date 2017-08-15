package com.sma.springboot;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;

import kr.bydelta.koala.twt.SentenceSplitter;
import kr.bydelta.koala.eunjeon.Tagger;
import kr.bydelta.koala.data.Sentence;

import kr.bydelta.koala.eunjeon.JavaDictionary;
import kr.bydelta.koala.POS;
import kr.bydelta.koala.POS$;
import java.util.LinkedList;

import java.util.*;

@RestController
public class KeywordController {
	
	//테스트용 데이터 셋
	public String test_speech_1 = "기업이 운영했던 기존의 관리 방식에서 탈피하여 디지털 기술을 가미한 운영 방식으로 바뀌게 되는 것을 '디지털 트랜스포메이션(Digital Transformation)'이라고 합니다. 디지털 트랜스포메이션은 기업규모와 관계없이 모든 비즈니스에서 반드시 필요합니다. 물류, IT, 금융, 의료서비스 등 다양한 산업군에서 디지털 트랜스포메이션을 요구할 수 있습니다. 고객과 직원에게 우수한 디지털 비즈니스 경험을 제공하려면 혁신적인 비즈니스 프로세스를 사용해야 합니다. 기업은 엄청난 속도로 진화하는 오늘날의 비즈니스 환경에 걸맞는 디지털 트랜스포메이션 솔루션을 제공할 수 있어야 합니다. 이것이 오늘날 세계적인 기업간의 경쟁에서 앞서 나가는 방법이 될 수 있습니다. 이를 위해서 운영 비용을 낮추고 고객 경험을 향상시키는 것이 핵심입니다. 디지털 전환은 단순히 최신기술을 수용하는 것이 아닌, 생각하는 방식과 조직 문화의 변화에 초점을 맞춥니다. 조직에서는 비즈니스의 변화, 비즈니스 요구 사항 등을 해결하고 이를 신속하게 대응할 수 있는 방법을 마련해야 합니다. 리더는 비즈니스 요구 사항을 충족시키고 혁신을 주도하며 지속적으로 비즈니스 환경을 개선해 나가야 합니다. 이것이 디지털 트랜스포메이션의 핵심입니다. 즉, 비즈니스 활동을 가속화시키고 비용을 낮추며, 제품 출시 시간을 단축하는 등 비즈니스 프로세스와 조직원의 역량과 마인드를 긍정적으로 변화시킬 수 있습니다. '디지털 트랜스포메이션'은 '비즈니스 트랜스포메이션'이라고도 불려집니다. '비즈니스 트랜스포메이션'은 혁신적인 기술, 소비자 고객 행동 및 시장 요구, 환경 요인 등의 요소에 의해 주도됩니다. 기술 혁신은 혼란을 가져올 수 있습니다. 기존 비즈니스 프로세스가 빅데이터, 클라우드, 인공지능 등의 최신 기술을 적용시키기 위해 기존 시스템에서 벗어나려하기 때문입니다. 점차 비즈니스에 새로운 가치가 부여되고 속도가 점점 빨라지면서 노력과 비용이 절감되는 등 좋은 결과가 나타나기 시작합니다. 비즈니스에 대해 고객이 기대하는 것과 요구사항이 무엇인지 파악하는 것도 중요합니다. 고객은 항상 사용하기 편리한 기술을 요구합니다. 이러한 이유로 고객의 요구 사항 및 기술의 발전이 비즈니스 요구 사항을 충족시킬 수 있는지에 대한 여부가 중요합니다. 기업의 성공적인 디지털 전환을 위해서는 아래와 같이 디지털 기술의 성숙도와 조직 문화가 중요합니다. 1. 고객 요구 사항 : 고객에게 모든 측면에서 유쾌한 경험을 제공하라. 이는 결과적으로 고객의 브랜드 충성도를 높임 2. 프로세스 오리엔테이션 (Process Orientation) : 데이터 중심의 의사 결정을 촉진해 성능 향상 및 운영 투명성을 향상시키는 디지털화 방법 3. 혁신적 비즈니스 : 기존의 비즈니스 요구사항을 뛰어 넘는 새로운 디지털 제품 및 디지털화 된 비즈니스 모델을 통해 변화하는 비즈니스 요구에 부응 디지털 전환은 비즈니스 세계에 변혁을 일으키는 폭풍과 같은 것 입니다. 이는 조직운영 뿐 아니라 산업 구조 전반에 걸쳐 영향을 미칩니다. 디지털 혁신이 비즈니스를 주도하고 생산의 변화를 가져오며 가치를 창출할 수 있도록 비즈니스 리더가 앞장서야 합니다.";
	public String test_speech_2 = "지난 해, 영국이 EU를 탈퇴한다는 브렉시트(Brexit)를 선언했을 때, 증권업계에서는 리스크 계수와 상품 가치를 재평가해야 하는 이슈가 생겼다. 이를 계산하기 위해서는 엄청난 컴퓨팅 리소스가 필요한 상황이었다. 보통 이런 업무는 리스크 관리 시스템이 하는데, 여러 대의 서버를 클러스터로 묶어 운영한다. 온프레미스 환경에서 서버를 추가 도입하기 위해서는 하드웨어를 발주하고 선정, 데이터센터에 설치, 운영체제 설치, 네트워크 구성/연결, 보안환경 구축 등의 과정이 필요하다. 이 모든 과정에 필요한 시간은 아무리 빨라도 보통 1.5개월 정도가 소요된다. 지금 당장 필요한 리소스임에도 불구하고 적지 않은   준비 기간이 필요한 것이다. 하지만 클라우드 인프라를 사용하고 있었다면 해당 기업은 몇 번의 클릭만으로 10분 안에 이를 해결할 수 있다. 이렇게 IaaS 도입만으로도 충분히 민첩성, 비즈니스 대응 효과를 누릴 수는 있다. 하지만 IaaS를 통한 민첩성은 딱 여기까지만이다. 인프라 구축 다음 단계인 개발환경 구축, 개발/테스트, 변경/배포, 운영/장애 처리에 있어서는 기존 온프레미스 방식과 별다른 차이가 없다. 즉, IaaS를 도입한다고 해서 소프트웨어 개발이나 배치 속도가 빨라지는 것은 아니라는 의미다. 비즈니스 민첩성은 하드웨어 내지 인프라 도입만을 의미하는 것이 아니며, 소프트웨어 개발/변경/테스트/배포를 포함하여 배치 속도까지 빨라졌을 때 비로소 완성된다.  그렇다면 이 단계에서의 민첩성은 어떻게 향상시킬 수 있을까. 단순히 ‘클라우드를 도입해야 한다’라는 기, 승, 전, 클라우드가 아니라 인프라 구축 이후 단계에서 비즈니스 속도를 높이기 위한 추가적인 ‘무언가’가 필요하다는 결론에 도달하게 된다. 빠른 배포 및 테스트 수행까지 연결된다. 이것이 PaaS의 본질이고 혁신이라고 할 수 있다. 개발 프로세스가 민첩해져야 할 때, PaaS는 그 효용성이 극대화된다. 디자인 및 개발 단계에서는 항상 소프트웨어 아키텍처에 대해 고민하기 마련 인데, 비즈니스 민첩성을 확보하기 위해서는 서비스간 연관관계를 최소화할 수 있는 아키텍처 디자인이 필요하다. 애플리케이션 개발 속도가 더딘 이유 가운데 하나는 복잡한 개발 환경에 있는데, 이는 서비스 개발자가 항상 고민해 오던 문제이며, 좀처럼 해결하기 어려운 문제이기도 하다. 마이크로서비스 아키텍처는 각 서비스와 데이터를 분리해 독립적인 단위로 배포, 수정, 확장할 수 있는 클라우드 네이티브 아키텍처다. 마이크로서비스는 각 서비스간 결합도를 낮춤으로써 독립적인 단위 배포가 가능하기 때문에 배포 시간과 재기동 시간을 드라마틱하게 단축시킬 수 있다. 또한 자바, 파이썬(Python), Node.js 등 각 서비스마다 서로 다른 최적의 개발 언어 및 환경을 선택할 수 있다는 장점이 있으며, 무엇보다 가장 큰 장점은 자원 확장이 필요한 서비스만 부분적으로 스케일 아웃(scale out)이 가능하다는 점이다. 이를 통해 트랜잭션이 가장 많이 발생하는 서비스의 리소스만 확장할 수 있으며, 장애 발생 시 주변 시스템에 주는 영향도를 최소화할 수 있다는 강점을 가지고 있다. 기업의 속도를 느리게 하는 요소 가운데 하나는 바로 조직 간 문제다.";
	public String test_speech_3 = "에너지 관리 전문 기업 슈나이더 일렉트릭코리아가 데이터센터 쿨링에 최적화된 DCIM 쿨링 옵티마이즈 솔루션을 제공한다고 8월14일 밝혔다. DCIM 쿨링 옵티마이즈는 데이터센터의 공기 흐름을 조절해 평균 온도를 유지하는 데 도움을 주는 솔루션이다. 데이터센터의 각 요소에 부착된 센서로 데이터를 수집·분석해 최적화된 상태로 제어하는 소프트웨어로 냉각장치 운영에 따른 전력 소모를 크게 낮춘다. 비용 절감 효과도 있다. 쿨링 옵티마이즈 소프트웨어는 모든 냉각 장치가 전 랙에 미치는 영향을 지속해서 학습한 다음 해당 정보를 사용해 냉각 장치 설정 및 공기의 흐름을 자동으로 조정한다. 이를 통해 핫스팟을 98%까지 제거해 냉각 전력 소비가 크게 감소하는 효과를 가져온다. 쿨링 옵티마이즈는 또 장비 이동, 업그레이드 또는 IT 부하 스윙과 같이 온도에 영향을 주는 모든 요소를 실시간으로 볼 수 있는 기능을 제공한다.";
	public String test_speech_4 = "세계적인 분석 기업 SAS코리아는 8월16일 자로 오병준 신임 대표이사를 선임한다고 발표했다. 오병준 신임 대표이사는 SAS코리아의 비즈니스를 총괄하며, 분석 전문 기업으로서 리더십을 강화하고 새로운 성장을 이끄는 데 주력할 방침이다. 오병준 대표이사는 27년 이상 글로벌 IT 기업에서 데이터 관리, 고급 분석, 데이터 웨어하우스, 클라우드 등 다양한 분야의 경험을 쌓아온 IT 전문가이다. SAS코리아에 합류하기 전 오병준 대표이사는 한국오라클에서 미들웨어 사업부 및 전략고객사업부 부사장으로 주요 고객을 담당하며 경영 리더십을 발휘했다. 특히, 국내 대기업과 전략적 제휴 모델 개발 업무를 성공적으로 추진했고, SaaS·PaaS·IaaS 등 클라우드 전 영역에 걸쳐 신규 고객사 확보를 진두지휘했다. 앞서 한국테라데이타 대표이사를 역임했으며, 한국오라클에서 퓨전 미들웨어 및 기술 사업본부를 총괄했다. 한국IBM에서 경력을 시작한 오병준 대표이사는 데이터 웨어하우스, 비즈니스 인텔리전스(BI), 데이터 마이닝, SOA, 웹스피어 등 다양한 사업 영역에서 세일즈 및 컨설팅 업무를 수행했다. 닉 리시 SAS 글로벌 세일즈 총괄 사장은 “글로벌 소프트웨어 시장에 대한 폭넓은 지식과 경험을 갖춘 오병준 신임 대표이사는 아태지역의 중요 시장인 한국에서 비즈니스 성장을 견인할 적임자”라며 “오 신임 대표이사가 고객의 비즈니스 혁신과 가치 창출을 지원하고 분석 선두 기업으로서 SAS의 새로운 도약을 이끌어갈 것으로 기대한다”라고 말했다. 오병준 SAS코리아 대표이사는 “글로벌 분석 시장의 리더로 자리매김한 SAS에 합류하게 되어 기쁘게 생각하는 동시에 막중한 책임감을 느낀다”라며, “SAS코리아가 지난 26년간 국내 분석 시장에서 구축한 확고한 입지를 더욱 강화하고, 임직원들과 함께 인공지능, 딥러닝, 개방형 분석 플랫폼, 클라우드 등 혁신적인 새로운 분석 기술을 제공해 고객과 함께 성장하는 비즈니스 파트너가 되도록 최선을 다할 것”이라고 포부를 밝혔다.";
	public String test_speech_5 = "소프트웨어 개발업체 CA테크놀로지스가 마이크로서비스 개발·배포에 최적화된 ‘CA API 관리 포트폴리오’ 신제품과 최신 기능을 8월10일 발표했다. 이날 라힘 바티아 CA테크놀로지스 개발자 제품 사업부 수석부사장 겸 총괄책임자가 직접 자리해 기술 동향과 신제품에 관한 내용을 전했다. 전통적으로 기업들은 제조 프로세스를 최적화하는 목표를 가지고 오랫동안 사업이 지속할 수 있는 로드맵을 설계해왔다. 하지만 디지털 변혁이 필요한 환경의 변화가 생겼고 컨테이너, 마이크로서비스, 클라우드, 애널리틱스, 빅데이터와 같은 새로운 기술이 늘어났다. 또한 전통적인 앤드포인트인 모바일에서 진화해 모바일, 다양한 앱, AR, 사물인터넷, 옵니채널과 같은 다양한 앤드포인트들이 등장하면서 적절한 인프라를 구축하는 것이 필요해졌다. 고객이 요구하는 다양성과 확장성도 높은 수준으로 상향 조정되며 전통적 아키텍처와 프로세스도 혁신이 일어나고 있다. API 관리 측면에서도 고객 성숙도가 높아지는 것이 보인다. 바티아 총괄책임자는 “과거에는 단순히 API 관리 측면에만 집중했다면, 이제는 관리뿐만 아니라 보안·데브옵스·테스팅·배포 등 다양한 측면이 API 관리 영역에 들어왔다”라며 “이제 API 전체 라이프사이클에 총체적 접근 방법이 중요하다. API 제공자의 요구, 소비자 요구 모두 충족시켜야 한다”라고 말했다. CA테크놀로지스는 ‘CA API 관리 포트폴리오’가 이 환경에 맞춰 API 전체 라이프사이클을 충족시킬 수 있게 강화한 솔루션이라고 말한다. CA테크놀로지스 고객 지원을 위한 ‘더 모던 소프트웨어 팩토리’ 시스템에서 집중하고자 하는 ‘애자일·데브옵스·보안’의 가치를 녹였다. 새로운 CA의 포트폴리오는 개발자, 엔터프라이즈 아키텍트, 디지털 리더가 개별 마이크로서비스를 연결 및 조정하는 API를 효과적으로 관리하고 모던 애플리케이션 아키텍처를 구현할 수 있게 구현됐다. CA API 관리 포트폴리오에는 ▲CA 라이브 API 크리에이터 ▲CA API 게이트웨이 ▲CA API 매니지먼트 SaaS ▲CA API 디벨로퍼 포털 ▲CA 모바일 API 게이트웨이/SDK가 포함된다. ‘CA 라이브 API 크리에이터’, ‘CA API 게이트웨이’는 도커 컨테이너 기반 배포 기능으로 마이크로서비스를 개발·배포·실행될 수 있게 한다. CA API 게이트웨이 안에 포함돼 현재 베타 버전으로 이용 가능한 신제품 ‘CA 마이크로게이트웨이’는 서비스 검색, 라우팅, 라스트마일 보완, 속도 제한 등 마이크로서비스의 로컬 정책을 지원한다. ‘CA API 디벨로퍼 포털’은 기업이 API를 원하는 환경과 방식으로 유연하게 관리하고 온프레미스, 하이브리드, 클라우드 환경으로 전환할 수 있게 한다. ‘CA 모바일 API 게이트웨이’와 CA의 API 관리 솔루션 중 ‘CA 고급 인증’의 통합 및 새로운 기능은 모바일 앱의 위험 기반 보안을 API로 확대 적용하면서 모바일 보안 개발을 앞당기도록 설계됐다. 개발자는 새로운 통합 모바일 SDK로 최종 사용자의 효율적인 경험을 유지하면서 인증 사항을 쉽게 통합할 수 있게 한다. 피트니스, 영양, 체중 감량 프로그램 분야 기업 ‘비치바디’는 CA 제품과 서비스를 이용해 IT 아키텍처를 혁신했다.";
	
	
	
	
	@RequestMapping("/keyword")
	@ResponseBody
	public Keyword keyword(@RequestBody DataObject input) {
		
		//초기셋팅
		add_user_words();
		SentenceSplitter sentSplit = new SentenceSplitter();
		HashMap<String, Integer> init = new HashMap<String, Integer>();
		
		//input json 단락 내용 가져오기
		String paragraph = input.getSpeech();
		
		//단락을 문장으로 분리
		List<String> sentences = sentSplit.jSentences(paragraph);
		System.out.println(sentences);
		
		//각 문장 내의 명사, 동사 추출 및 저장
		List<String> nouns_verbs = return_nouns_verbs(sentences);
		nouns_verbs = remove_stopwords(nouns_verbs);

		//상위 출현 10개 추출 및 정렬
		List<String> words = sortByValue(count(nouns_verbs)).subList(0, 9);
		List<Integer> freqs = getFreqs(count(nouns_verbs), words);
		
		for (int i = 0; i < sortByValue(count(nouns_verbs)).subList(0, 9).size(); i++) {
			init.put(words.get(i), freqs.get(i));
		}
		
		Keyword keywords = new Keyword(init, input.getDivision());
		
		return keywords;
	}
		
	
	@RequestMapping("/similarity")
	@ResponseBody
	public Similarity similarity(@RequestBody DataMeeting input) {
		
		//construct
		HashMap<Integer, Double> meetings_similar = new HashMap<Integer, Double>();
		Similarity output = new Similarity();
		HashMap<Integer, String> test_data = new HashMap<Integer, String>();
				
		// test data
		test_data.put(1, test_speech_1);
		test_data.put(2, test_speech_2);
		test_data.put(3, test_speech_3);
		test_data.put(4, test_speech_4);
		test_data.put(5, test_speech_5);
		
		
		Integer meeting_id = input.getId();
		
		for (int i = 1; i < test_data.size()+1; i++) {
			meetings_similar.put(i,cal_sim(test_data.get(meeting_id), test_data.get(i)));
		}
		meetings_similar.remove(input.getId());
		
		output.setId(input.getId());
		output.setsimilarity(meetings_similar);
		System.out.println(output);
		return output;
	}
	
	public Double cal_sim(String doc1, String doc2) {

		Double ratio = 0.0;
		Double a = 0.0;
		Double b = 0.0;
		Double c = 0.0;
		
		// 전체 단어 목록을 만들기 위해 doc1 doc2 통합
		String doc_total = doc1 + doc2;
				
		// 여기서부터 키워드 추출
		SentenceSplitter sentSplit = new SentenceSplitter();
		List<String> sentences_1 = sentSplit.jSentences(doc1);
		List<String> sentences_2 = sentSplit.jSentences(doc2);
		List<String> sentences_total = sentSplit.jSentences(doc_total);
		
		// return_nouns_verbs는 그냥 명사 동사 추출하는건데 이부분까지는 동규씨 키워드 추출 알고리즘 쓰시면 될것 같아요!
		List<String> terms = return_nouns_verbs(sentences_total);
		terms = remove_stopwords(terms);
		Set<String> terms_unique = new HashSet<String>();
		
		// unique한 단어 집합 만들기
		for (String word : terms) {
			terms_unique.add(word);
		}
		
		// doc1 set doc2 set
		List<String> terms_1 = return_nouns_verbs(sentences_1);
		terms_1 = remove_stopwords(terms_1);
		List<String> terms_2 = return_nouns_verbs(sentences_2);
		terms_2 = remove_stopwords(terms_2);
				
		// 연관도 계산
		for (String term : terms) {
			if (terms_1.contains(term) && terms_2.contains(term)) {
				a = a + 1;
			} else if (terms_1.contains(term) && !terms_2.contains(term)) {
				b = b + 1;
			} else if (!terms_1.contains(term) && terms_2.contains(term)) {
				c = c + 1;
			}
		}
		
		ratio = a/(a+b+c);
		
		//연과도 반환
		return ratio;
	}

	
	public List<String> return_nouns_verbs(List<String> sentences) {
		//초기화
		List<String> nouns = new ArrayList<String>();
		List<String> verbs = new ArrayList<String>();
		Tagger tagger = new Tagger();
		
		//각 문장별 명사 추출
		for (int i=0; i < sentences.size(); i++  ) {
			Sentence analyzed = tagger.tagSentence(sentences.get(i));
					
			for (int j=0; j < analyzed.jNouns().size(); j++ ) {
				
				nouns.add(analyzed.jNouns().get(j).toString().split("/")[0].split(" ")[1]);
				
			}
			
			for (int j=0; j < analyzed.jVerbs().size(); j++ ) {
				
				verbs.add(analyzed.jVerbs().get(j).toString().split("/")[0].split(" ")[1]);
				
			}
		}
		List<String> nouns_verbs = new ArrayList<String>(nouns); nouns_verbs.addAll(verbs);
		nouns_verbs = remove_stopwords(nouns_verbs);
		
		return nouns_verbs;
	}	
	
	//Sentences Array 생성
	public ArrayList<List<String>> get_sentences(List<String> sentences) {
		//초기화
		ArrayList<List<String>> result = new ArrayList<List<String>>();
		
		Tagger tagger = new Tagger();
		
		//각 문장별 명사 추출
		for (int i=0; i < sentences.size(); i++  ) {
			Sentence sentence = tagger.tagSentence(sentences.get(i));
			
			List<String> new_sentence = new ArrayList<String>();
			
			for (int j=0; j < sentence.jNouns().size(); j++ ) {
				
				new_sentence.add(sentence.jNouns().get(j).toString().split("/")[0].split(" ")[1]);
				
			}
			
			for (int j=0; j < sentence.jVerbs().size(); j++ ) {
				
				new_sentence.add(sentence.jVerbs().get(j).toString().split("/")[0].split(" ")[1]);
				
			}
			new_sentence = remove_stopwords(new_sentence);
			result.add(new_sentence);
			
		}
		
		return result;
	}

	
	//단어 추출 횟수를 단어: 숫자로 맵핑
	public HashMap<String, Integer> count(List<String> words) {
		HashMap<String , Integer> result = new HashMap<String , Integer>();
		for (int i=0; i < words.size(); i++) {
			
			if (!result.containsKey(words.get(i))) {
				result.put(words.get(i), 1);
			}
			else {
				result.put(words.get(i), result.get(words.get(i)) + 1);
			}
		}
		
		return result;
	}
	
	//정렬
	public static List<String> sortByValue(final Map map) {
        List<String> list = new ArrayList();
        list.addAll(map.keySet());
         
        Collections.sort(list,new Comparator() {
             
            public int compare(Object o1,Object o2) {
                Object v1 = map.get(o1);
                Object v2 = map.get(o2);
                 
                return ((Comparable) v2).compareTo(v1);
            }
             
        });
//        Collections.reverse(list); // 주석시 오름차순
        return list;
    }
	
	//단어에 맞는 frequency 추출
	public List<Integer> getFreqs(HashMap<String, Integer> words, List<String> terms){
		List<Integer> freqs = new ArrayList<Integer>();
		for (String term : terms) {
			freqs.add(words.get(term));
		}
		
		return freqs;
	}
	
	//연관단어에 맞는 correlation 추출
	public List<Double> getCorrelations(HashMap<String, Double> words, List<String> terms){
		List<Double> correlations = new ArrayList<Double>();
		for (String term : terms) {
			correlations.add(words.get(term));
		}
		
		return correlations;
	}
	
	//필요 없는 단어 제거
	public static List<String> remove_stopwords(List<String> words) {
		List<String> stopwords = Arrays.asList("되","보","수","있","!","\"","$","%","&","'","(",")","*","+",",","-",".","...","0","1","2","3","4","5","6","7","8","9",";","<","=",">","?","@","\\","^","_","`","|","~","·","—","——","‘","’","“","”","…","、","。","〈","〉","《","》","가","가까스로","가령","각","각각","각자","각종","갖고말하자면","같다","같이","개의치않고","거니와","거바","거의","것","것과 같이","것들","게다가","게우다","겨우","견지에서","결과에 이르다","결국","결론을 낼 수 있다","겸사겸사","고려하면","고로","곧","공동으로","과","과연","관계가 있다","관계없이","관련이 있다","관하여","관한","관해서는","구","구체적으로","구토하다","그","그들","그때","그래","그래도","그래서","그러나","그러니","그러니까","그러면","그러므로","그러한즉","그런 까닭에","그런데","그런즉","그럼","그럼에도 불구하고","그렇게 함으로써","그렇지","그렇지 않다면","그렇지 않으면","그렇지만","그렇지않으면","그리고","그리하여","그만이다","그에 따르는","그위에","그저","그중에서","그치지 않다","근거로","근거하여","기대여","기점으로","기준으로","기타","까닭으로","까악","까지","까지 미치다","까지도","꽈당","끙끙","끼익","나","나머지는","남들","남짓","너","너희","너희들","네","넷","년","논하지 않다","놀라다","누가 알겠는가","누구","다른","다른 방면으로","다만","다섯","다소","다수","다시 말하자면","다시말하면","다음","다음에","다음으로","단지","답다","당신","당장","대로 하다","대하면","대하여","대해 말하자면","대해서","댕그","더구나","더군다나","더라도","더불어","더욱더","더욱이는","도달하다","도착하다","동시에","동안","된바에야","된이상","두번째로","둘","둥둥","뒤따라","뒤이어","든간에","들","등","등등","딩동","따라","따라서","따위","따지지 않다","딱","때","때가 되어","때문에","또","또한","뚝뚝","라 해도","령","로","로 인하여","로부터","로써","륙","를","마음대로","마저","마저도","마치","막론하고","만 못하다","만약","만약에","만은 아니다","만이 아니다","만일","만큼","말하자면","말할것도 없고","매","매번","메쓰겁다","몇","모","모두","무렵","무릎쓰고","무슨","무엇","무엇때문에","물론","및","바꾸어말하면","바꾸어말하자면","바꾸어서 말하면","바꾸어서 한다면","바꿔 말하면","바로","바와같이","밖에 안된다","반대로","반대로 말하자면","반드시","버금","보는데서","보다더","보드득","본대로","봐","봐라","부류의 사람들","부터","불구하고","불문하고","붕붕","비걱거리다","비교적","비길수 없다","비로소","비록","비슷하다","비추어 보아","비하면","뿐만 아니라","뿐만아니라","뿐이다","삐걱","삐걱거리다","사","삼","상대적으로 말하자면","생각한대로","설령","설마","설사","셋","소생","소인","솨","쉿","습니까","습니다","시각","시간","시작하여","시초에","시키다","실로","심지어","아","아니","아니나다를가","아니라면","아니면","아니었다면","아래윗","아무거나","아무도","아야","아울러","아이","아이고","아이구","아이야","아이쿠","아하","아홉","안 그러면","않기 위하여","않기 위해서","알 수 있다","알았어","앗","앞에서","앞의것","야","약간","양자","어","어기여차","어느","어느 년도","어느것","어느곳","어느때","어느쪽","어느해","어디","어때","어떠한","어떤","어떤것","어떤것들","어떻게","어떻해","어이","어째서","어쨋든","어쩔수 없다","어찌","어찌됏든","어찌됏어","어찌하든지","어찌하여","언제","언젠가","얼마","얼마 안 되는 것","얼마간","얼마나","얼마든지","얼마만큼","얼마큼","엉엉","에","에 가서","에 달려 있다","에 대해","에 있다","에 한하다","에게","에서","여","여기","여덟","여러분","여보시오","여부","여섯","여전히","여차","연관되다","연이서","영","영차","옆사람","예","예를 들면","예를 들자면","예컨대","예하면","오","오로지","오르다","오자마자","오직","오호","오히려","와","와 같은 사람들","와르르","와아","왜","왜냐하면","외에도","요만큼","요만한 것","요만한걸","요컨대","우르르","우리","우리들","우선","우에 종합한것과같이","운운","월","위에서 서술한바와같이","위하여","위해서","윙윙","육","으로","으로 인하여","으로서","으로써","을","응","응당","의","의거하여","의지하여","의해","의해되다","의해서","이","이 되다","이 때문에","이 밖에","이 외에","이 정도의","이것","이곳","이때","이라면","이래","이러이러하다","이러한","이런","이럴정도로","이렇게 많은 것","이렇게되면","이렇게말하자면","이렇구나","이로 인하여","이르기까지","이리하여","이만큼","이번","이봐","이상","이어서","이었다","이와 같다","이와 같은","이와 반대로","이와같다면","이외에도","이용하여","이유만으로","이젠","이지만","이쪽","이천구","이천육","이천칠","이천팔","인 듯하다","인젠","일","일것이다","일곱","일단","일때","일반적으로","일지라도","임에 틀림없다","입각하여","입장에서","잇따라","있다","자","자기","자기집","자마자","자신","잠깐","잠시","저","저것","저것만큼","저기","저쪽","저희","전부","전자","전후","점에서 보아","정도에 이르다","제","제각기","제외하고","조금","조차","조차도","졸졸","좀","좋아","좍좍","주룩주룩","주저하지 않고","줄은 몰랏다","줄은모른다","중에서","중의하나","즈음하여","즉","즉시","지든지","지만","지말고","진짜로","쪽으로","차라리","참","참나","첫번째로","쳇","총적으로","총적으로 말하면","총적으로 보면","칠","콸콸","쾅쾅","쿵","타다","타인","탕탕","토하다","통하여","툭","퉤","틈타","팍","팔","퍽","펄렁","하","하게될것이다","하게하다","하겠는가","하고 있다","하고있었다","하곤하였다","하구나","하기 때문에","하기 위하여","하기는한데","하기만 하면","하기보다는","하기에","하나","하느니","하는 김에","하는 편이 낫다","하는것도","하는것만 못하다","하는것이 낫다","하는바","하더라도","하도다","하도록시키다","하도록하다","하든지","하려고하다","하마터면","하면 할수록","하면된다","하면서","하물며","하여금","하여야","하자마자","하지 않는다면","하지 않도록","하지마","하지마라","하지만","하하","한 까닭에","한 이유는","한 후","한다면","한다면 몰라도","한데","한마디","한적이있다","한켠으로는","한항목","할 따름이다","할 생각이다","할 줄 안다","할 지경이다","할 힘이 있다","할때","할만하다","할망정","할뿐","할수있다","할수있어","할줄알다","할지라도","할지언정","함께","해도된다","해도좋다","해봐요","해서는 안된다","해야한다","해요","했어요","향하다","향하여","향해서","허","허걱","허허","헉","헉헉","헐떡헐떡","형식으로 쓰여","혹시","혹은","혼자","훨씬","휘익","휴","흐흐","흥","힘입어","︿","！","＃","＄","％","＆","（","）","＊","＋","，","０","１","２","３","４","５","６","７","８","９","：","；","＜","＞","？","＠","［","］","｛","｜","｝","～","￥");
				
		words.removeAll(stopwords);
		return words;
	}
	
	//사용자 정의 단어 추가
	public void add_user_words() {
		LinkedList<String> morphemes = new LinkedList<>();
		LinkedList<POS$.Value> pos = new LinkedList<>();

		morphemes.add("트랜스포메이션");
		pos.add(POS.NNP()); 
		morphemes.add("빅데이터");
		pos.add(POS.NNP());
//
//		morphemes.add("구글하");
//		pos.add(POS.VV()); /* 동사 '구글하다' 추가 */

		// 이 동작을 하지 않으면 추가되지 않습니다.
		JavaDictionary.addUserDictionary(morphemes, pos);
	}
}
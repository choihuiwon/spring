package gocamping;

public class SpotDTO {

	private int contentId;
	private String facltNm;
	private String lineIntro;
	private String intro;
	private String bizrno;
	private String manageSttus;
	private String hvofBgnde;
	private String hvofEnddle;
	private String featureNm;
	private String induty;
	private String lctCl;
	private String doNm;
	private String sigunguNm;
	private int zipcode;
	private String addr1;
	private String addr2;
	private String tel;
	private String homepage;
	private int gnrlSiteCo;
	private int autoSiteCo;
	private int glampSiteCo;
	private int caravSiteCo;
	private int indvdlCaravSiteCo;
	private int siteBottomCl1;
	private int siteBottomCl2;
	private int siteBottomCl3;
	private int siteBottomCl4;
	private int siteBottomCl5;
	private String glampInnerFclty;
	private String caravInnerFclty;
	private String operPdCl;
	private String trlerAcmpnyAt;
	private String caravAcmpnyAt;
	private int toiletCo;
	private int swrmCo;
	private int wtrplCo;
	private String brazierCl;
	private String sbrsCl;
	private String sbrsEtc;
	private String posblFcltyCl;
	private String posblFcltyEtc;
	private String exprnProgrm;
	private String themaEnvrnCl;
	private String eqpmnLendCl;
	private String animalCmgCl;
	private String tourEraCl;
	private String firstImageUrl;
	
	private double star;
	private int review_count;
	public SpotDTO(int contentId, String facltNm, String lineIntro, String intro, String bizrno, String manageSttus,
			String hvofBgnde, String hvofEnddle, String featureNm, String induty, String lctCl, String doNm,
			String sigunguNm, int zipcode, String addr1, String addr2, String tel, String homepage, int gnrlSiteCo,
			int autoSiteCo, int glampSiteCo, int caravSiteCo, int indvdlCaravSiteCo, int siteBottomCl1,
			int siteBottomCl2, int siteBottomCl3, int siteBottomCl4, int siteBottomCl5, String glampInnerFclty,
			String caravInnerFclty, String operPdCl, String trlerAcmpnyAt, String caravAcmpnyAt, int toiletCo, int swrmCo,
			int wtrplCo, String brazierCl, String sbrsCl, String sbrsEtc, String posblFcltyCl, String posblFcltyEtc,
			String exprnProgrm, String themaEnvrnCl, String eqpmnLendCl, String animalCmgCl, String tourEraCl,
			String firstImageUrl, double star, int review_count) {
		super();
		this.contentId = contentId;
		this.facltNm = facltNm;
		this.lineIntro = lineIntro;
		this.intro = intro;
		this.bizrno = bizrno;
		this.manageSttus = manageSttus;
		this.hvofBgnde = hvofBgnde;
		this.hvofEnddle = hvofEnddle;
		this.featureNm = featureNm;
		this.induty = induty;
		this.lctCl = lctCl;
		this.doNm = doNm;
		this.sigunguNm = sigunguNm;
		this.zipcode = zipcode;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.tel = tel;
		this.homepage = homepage;
		this.gnrlSiteCo = gnrlSiteCo;
		this.autoSiteCo = autoSiteCo;
		this.glampSiteCo = glampSiteCo;
		this.caravSiteCo = caravSiteCo;
		this.indvdlCaravSiteCo = indvdlCaravSiteCo;
		this.siteBottomCl1 = siteBottomCl1;
		this.siteBottomCl2 = siteBottomCl2;
		this.siteBottomCl3 = siteBottomCl3;
		this.siteBottomCl4 = siteBottomCl4;
		this.siteBottomCl5 = siteBottomCl5;
		this.glampInnerFclty = glampInnerFclty;
		this.caravInnerFclty = caravInnerFclty;
		this.operPdCl = operPdCl;
		this.trlerAcmpnyAt = trlerAcmpnyAt;
		this.caravAcmpnyAt = caravAcmpnyAt;
		this.toiletCo = toiletCo;
		this.swrmCo = swrmCo;
		this.wtrplCo = wtrplCo;
		this.brazierCl = brazierCl;
		this.sbrsCl = sbrsCl;
		this.sbrsEtc = sbrsEtc;
		this.posblFcltyCl = posblFcltyCl;
		this.posblFcltyEtc = posblFcltyEtc;
		this.exprnProgrm = exprnProgrm;
		this.themaEnvrnCl = themaEnvrnCl;
		this.eqpmnLendCl = eqpmnLendCl;
		this.animalCmgCl = animalCmgCl;
		this.tourEraCl = tourEraCl;
		this.firstImageUrl = firstImageUrl;
		this.star = star;
		this.review_count = review_count;
	}
	public SpotDTO(int contentId2, String facltNm2, String lineIntro2, String intro2, String bizrno2,
			String manageSttus2, String hvofBgnde2, String hvofEnddle2, String featureNm2, String induty2,
			String lctCl2, String doNm2, String sigunguNm2, int zipcode2, String addr12, String addr22, String tel2,
			String homepage2, int gnrlSiteCo2, int autoSiteCo2, int glampSiteCo2, int caravSiteCo2,
			int indvdlCaravSiteCo2, int siteBottomCl12, int siteBottomCl22, int siteBottomCl32, int siteBottomCl42,
			int siteBottomCl52, String glampInnerFclty2, String caravInnerFclty2, String operPdCl2,
			String trlerAcmpnyAt2, String caravAcmpnyAt2, int toiletCo2, int swrmCo2, int wtrplCo2, String brazierCl2,
			String sbrsCl2, String sbrsEtc2, String posblFcltyCl2, String posblFcltyEtc2, String exprnProgrm2,
			String themaEnvrnCl2, String eqpmnLendCl2, String animalCmgCl2, String tourEraCl2, String firstImageUrl2) {
		// TODO Auto-generated constructor stub
	}
	public int getContentId() {
		return contentId;
	}
	public void setContentId(int contentId) {
		this.contentId = contentId;
	}
	public String getFacltNm() {
		return facltNm;
	}
	public void setFacltNm(String facltNm) {
		this.facltNm = facltNm;
	}
	public String getLineIntro() {
		return lineIntro;
	}
	public void setLineIntro(String lineIntro) {
		this.lineIntro = lineIntro;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getBizrno() {
		return bizrno;
	}
	public void setBizrno(String bizrno) {
		this.bizrno = bizrno;
	}
	public String getManageSttus() {
		return manageSttus;
	}
	public void setManageSttus(String manageSttus) {
		this.manageSttus = manageSttus;
	}
	public String getHvofBgnde() {
		return hvofBgnde;
	}
	public void setHvofBgnde(String hvofBgnde) {
		this.hvofBgnde = hvofBgnde;
	}
	public String getHvofEnddle() {
		return hvofEnddle;
	}
	public void setHvofEnddle(String hvofEnddle) {
		this.hvofEnddle = hvofEnddle;
	}
	public String getFeatureNm() {
		return featureNm;
	}
	public void setFeatureNm(String featureNm) {
		this.featureNm = featureNm;
	}
	public String getInduty() {
		return induty;
	}
	public void setInduty(String induty) {
		this.induty = induty;
	}
	public String getLctCl() {
		return lctCl;
	}
	public void setLctCl(String lctCl) {
		this.lctCl = lctCl;
	}
	public String getDoNm() {
		return doNm;
	}
	public void setDoNm(String doNm) {
		this.doNm = doNm;
	}
	public String getSigunguNm() {
		return sigunguNm;
	}
	public void setSigunguNm(String sigunguNm) {
		this.sigunguNm = sigunguNm;
	}
	public int getZipcode() {
		return zipcode;
	}
	public void setZipcode(int zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getHomepage() {
		return homepage;
	}
	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}
	public int getGnrlSiteCo() {
		return gnrlSiteCo;
	}
	public void setGnrlSiteCo(int gnrlSiteCo) {
		this.gnrlSiteCo = gnrlSiteCo;
	}
	public int getAutoSiteCo() {
		return autoSiteCo;
	}
	public void setAutoSiteCo(int autoSiteCo) {
		this.autoSiteCo = autoSiteCo;
	}
	public int getGlampSiteCo() {
		return glampSiteCo;
	}
	public void setGlampSiteCo(int glampSiteCo) {
		this.glampSiteCo = glampSiteCo;
	}
	public int getCaravSiteCo() {
		return caravSiteCo;
	}
	public void setCaravSiteCo(int caravSiteCo) {
		this.caravSiteCo = caravSiteCo;
	}
	public int getIndvdlCaravSiteCo() {
		return indvdlCaravSiteCo;
	}
	public void setIndvdlCaravSiteCo(int indvdlCaravSiteCo) {
		this.indvdlCaravSiteCo = indvdlCaravSiteCo;
	}
	public int getSiteBottomCl1() {
		return siteBottomCl1;
	}
	public void setSiteBottomCl1(int siteBottomCl1) {
		this.siteBottomCl1 = siteBottomCl1;
	}
	public int getSiteBottomCl2() {
		return siteBottomCl2;
	}
	public void setSiteBottomCl2(int siteBottomCl2) {
		this.siteBottomCl2 = siteBottomCl2;
	}
	public int getSiteBottomCl3() {
		return siteBottomCl3;
	}
	public void setSiteBottomCl3(int siteBottomCl3) {
		this.siteBottomCl3 = siteBottomCl3;
	}
	public int getSiteBottomCl4() {
		return siteBottomCl4;
	}
	public void setSiteBottomCl4(int siteBottomCl4) {
		this.siteBottomCl4 = siteBottomCl4;
	}
	public int getSiteBottomCl5() {
		return siteBottomCl5;
	}
	public void setSiteBottomCl5(int siteBottomCl5) {
		this.siteBottomCl5 = siteBottomCl5;
	}
	public String getGlampInnerFclty() {
		return glampInnerFclty;
	}
	public void setGlampInnerFclty(String glampInnerFclty) {
		this.glampInnerFclty = glampInnerFclty;
	}
	public String getCaravInnerFclty() {
		return caravInnerFclty;
	}
	public void setCaravInnerFclty(String caravInnerFclty) {
		this.caravInnerFclty = caravInnerFclty;
	}
	public String getOperPdCl() {
		return operPdCl;
	}
	public void setOperPdCl(String operPdCl) {
		this.operPdCl = operPdCl;
	}
	public String getTrlerAcmpnyAt() {
		return trlerAcmpnyAt;
	}
	public void setTrlerAcmpnyAt(String trlerAcmpnyAt) {
		this.trlerAcmpnyAt = trlerAcmpnyAt;
	}
	public String getCaravAcmpnyAt() {
		return caravAcmpnyAt;
	}
	public void setCaravAcmpnyAt(String caravAcmpnyAt) {
		this.caravAcmpnyAt = caravAcmpnyAt;
	}
	public int getToiletCo() {
		return toiletCo;
	}
	public void setToiletCo(int toiletCo) {
		this.toiletCo = toiletCo;
	}
	public int getSwrmCo() {
		return swrmCo;
	}
	public void setSwrmCo(int swrmCo) {
		this.swrmCo = swrmCo;
	}
	public int getWtrplCo() {
		return wtrplCo;
	}
	public void setWtrplCo(int wtrplCo) {
		this.wtrplCo = wtrplCo;
	}
	public String getBrazierCl() {
		return brazierCl;
	}
	public void setBrazierCl(String brazierCl) {
		this.brazierCl = brazierCl;
	}
	public String getSbrsCl() {
		return sbrsCl;
	}
	public void setSbrsCl(String sbrsCl) {
		this.sbrsCl = sbrsCl;
	}
	public String getSbrsEtc() {
		return sbrsEtc;
	}
	public void setSbrsEtc(String sbrsEtc) {
		this.sbrsEtc = sbrsEtc;
	}
	public String getPosblFcltyCl() {
		return posblFcltyCl;
	}
	public void setPosblFcltyCl(String posblFcltyCl) {
		this.posblFcltyCl = posblFcltyCl;
	}
	public String getPosblFcltyEtc() {
		return posblFcltyEtc;
	}
	public void setPosblFcltyEtc(String posblFcltyEtc) {
		this.posblFcltyEtc = posblFcltyEtc;
	}
	public String getExprnProgrm() {
		return exprnProgrm;
	}
	public void setExprnProgrm(String exprnProgrm) {
		this.exprnProgrm = exprnProgrm;
	}
	public String getThemaEnvrnCl() {
		return themaEnvrnCl;
	}
	public void setThemaEnvrnCl(String themaEnvrnCl) {
		this.themaEnvrnCl = themaEnvrnCl;
	}
	public String getEqpmnLendCl() {
		return eqpmnLendCl;
	}
	public void setEqpmnLendCl(String eqpmnLendCl) {
		this.eqpmnLendCl = eqpmnLendCl;
	}
	public String getAnimalCmgCl() {
		return animalCmgCl;
	}
	public void setAnimalCmgCl(String animalCmgCl) {
		this.animalCmgCl = animalCmgCl;
	}
	public String getTourEraCl() {
		return tourEraCl;
	}
	public void setTourEraCl(String tourEraCl) {
		this.tourEraCl = tourEraCl;
	}
	public String getFirstImageUrl() {
		return firstImageUrl;
	}
	public void setFirstImageUrl(String firstImageUrl) {
		this.firstImageUrl = firstImageUrl;
	}
	public double getStar() {
		return star;
	}
	public void setStar(double star) {
		this.star = star;
	}
	public int getReview_count() {
		return review_count;
	}
	public void setReview_count(int review_count) {
		this.review_count = review_count;
	}
	
	
}

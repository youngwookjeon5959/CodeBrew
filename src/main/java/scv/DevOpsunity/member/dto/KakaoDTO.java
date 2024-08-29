package scv.DevOpsunity.member.dto;

public class KakaoDTO {

    private String id;
    private String kakao_mail;
    private String name;
    private String access_token;

    public KakaoDTO() {
        // TODO Auto-generated constructor stub
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getKakao_mail() {
        return kakao_mail;
    }

    public void setKakao_mail(String kakao_mail) {
        this.kakao_mail = kakao_mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    @Override
    public String toString() {
        return "KakaoVO [id=" + id + ", kakao_mail=" + kakao_mail + ", nickname=" + name  + ", access_token=" + access_token + "]";
    }

}

package Pojo;

import java.util.List;

public class Courses {

    private List<CourseWebAutomation> webAutomation;
    private List<CourseApi> api;
    private List<CourseMobile> mobile;

    public List<CourseWebAutomation> getWebAutomation() {
        return webAutomation;
    }

    public void setWebAutomation(List<CourseWebAutomation> webAutomation) {
        this.webAutomation = webAutomation;
    }

    public List<CourseApi> getApi() {
        return api;
    }

    public void setApi(List<CourseApi> api) {
        this.api = api;
    }

    public List<CourseMobile> getMobile() {
        return mobile;
    }

    public void setMobile(List<CourseMobile> mobile) {
        this.mobile = mobile;
    }
}

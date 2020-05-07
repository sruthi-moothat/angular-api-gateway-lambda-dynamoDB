package homework4;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String emailId;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String tel;
    private String sdate;
    private String thingsLiked;
    private String interest;
    private String recommend;



    public Student(String json) {
        // it is used to create java object from json
        Gson gson = new Gson();
        Student request = gson.fromJson(json, Student.class);
        this.id = request.getId();
        this.firstName = request.getFirstName();
        this.lastName = request.getLastName();
        this.emailId = request.getEmailId();
        this.address = request.getAddress();
        this.city = request.getCity();
        this.state = request.getState();
        this.zip = request.getZip();
        this.tel = request.getTel();
        this.sdate = request.getSdate();
        this.thingsLiked = request.getThingsLiked();
        this.interest = request.getInterest();
        this.recommend = request.getRecommend();


    }

    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getThingsLiked() {
        return thingsLiked;
    }

    public void setThingsLiked(String thingsLiked) {
        this.thingsLiked = thingsLiked;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }
}

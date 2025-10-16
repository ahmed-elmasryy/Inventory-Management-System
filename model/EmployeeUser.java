
public class EmployeeUser {

    private String employeeId;
    private String Name;
    private String email;
    private String address;
    private String phoneNumber;


    public EmployeeUser(String employeeID, String name, String email,
                        String address, String phoneNumber){
        this.employeeId=employeeID;
        this.Name=name;
        this.email=email;
        this.address=address;
        this.phoneNumber=phoneNumber;
    }

    public String lineRepresentation(){
        return (this.employeeId +","+this.Name+","+this.email+","+this.address+","+this.phoneNumber);
    }

    public String getSearchKey(){
        return this.employeeId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
}

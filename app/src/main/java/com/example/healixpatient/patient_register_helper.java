package com.example.healixpatient;

public class patient_register_helper {


        private String FullName;
        private String Age;
        private String Phone_num;
        private String Gender;
        private String Email;
        private String Pass;
        private String Height;
        private String Weight;
        private String Allergy;
        private String Meds;
        private String Medcon;

public patient_register_helper(){


}

        public patient_register_helper(String fullName, String age, String phone_num, String gender,  String height, String weight, String allergy,String meds,String medcon) {
                FullName = fullName;
                Age = age;
                Phone_num = phone_num;
                Gender = gender;
                Height = height;
                Weight = weight;
                Allergy = allergy;
                Meds = meds;
                Medcon = medcon;
        }

        public String getFullName() {
                return FullName;
        }

        public void setFullName(String fullName) {
                FullName = fullName;
        }

        public String getAge() {
                return Age;
        }

        public void setAge(String age) {
                Age = age;
        }

        public String getPhone_num() {
                return Phone_num;
        }

        public void setPhone_num(String phone_num) {
                Phone_num = phone_num;
        }

        public String getGender() {
                return Gender;
        }

        public void setGender(String gender) {
                Gender = gender;
        }

        public String getEmail() {
                return Email;
        }

        public void setEmail(String email) {
                Email = email;
        }

        public String getPass() {
                return Pass;
        }

        public void setPass(String pass) {
                Pass = pass;
        }

        public String getHeight() {
                return Height;
        }

        public void setHeight(String height) {
                Height = height;
        }

        public String getWeight() {
                return Weight;
        }

        public void setWeight(String weight) {
                Weight = weight;
        }

        public String getAllergy() {
                return Allergy;
        }

        public void setAllergy(String allergy) {
                Allergy = allergy;
        }

        public String getMeds() {
                return Meds;
        }

        public void setMeds(String meds) {
                Meds = meds;
        }

        public String getMedcon() {
                return Medcon;
        }

        public void setMedcon(String medcon) {
                Medcon = medcon;
        }
}


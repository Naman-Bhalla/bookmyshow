package com.bookmyshow.app.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "admins")
public class Admin extends Exposed {
    private String designation;
    private String phone1, phone2, phone3;
    private Long numberOfYearsInTheCompany;

    @OneToOne
    private User user; // this profile belongs to this user

    public Admin(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "designation='" + designation + '\'' +
                ", phone1='" + phone1 + '\'' +
                ", phone2='" + phone2 + '\'' +
                ", phone3='" + phone3 + '\'' +
                ", numberOfYearsInTheCompany=" + numberOfYearsInTheCompany +
                ", user=" + user +
                super.toString() +
                '}';
    }
}

// if (user.getRoles().contains(ADMIN)) {
//   Admin admin = new Admin(user);
//   admin.addCity(DELHI);
//   admin.addCinema(PVT);
// }

// class CityController {
//   City addCity(String name, String pincode, User user) {
//        if (!user.getRoles().contains(ADMIN)) {
//           return 403Error;
//        }
//        cityService.addCity(name, pincode);
//   }
//
// }
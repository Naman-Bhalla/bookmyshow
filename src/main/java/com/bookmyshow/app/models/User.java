package com.bookmyshow.app.models;

import com.bookmyshow.app.exceptions.validation.InvalidUsernameException;
import com.bookmyshow.app.exceptions.validation.PasswordTooSimpleException;
import com.bookmyshow.app.repositories.interfaces.CustomerRepository;
import com.bookmyshow.app.services.passwordencoder.PasswordEncoder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User extends Auditable {
    // authentication
    private String username;
    private String hashedSaltedPassword;

    // authorization
    // private List<Role> O(N)
    @ManyToMany
    private Set<Role> roles = new HashSet<>(); // O(1)

    public User(String username) {
        this.username = username;
    }

    public void setUsername(String username) {
        // validate
        if (username.length() < 2) {
            throw new InvalidUsernameException("Must have atleast 3 letters");
        }
        this.username = username;
    }

    // hashing + salting (every user we have a separate random salt)
    // best practices for securing a password
    // stored_password = f(h(password), salt)
    // bcrypt password encoder
    public void setPassword(String password, PasswordEncoder passwordEncoder) {
        if (password.length() < 8) {
            throw new PasswordTooSimpleException("must have atleast 8 characters");
        }
        String salt = "salt"; // from some service
        this.hashedSaltedPassword = passwordEncoder.encode(password + salt);
        this.hashedSaltedPassword += ";" + salt;

        CustomerRepository customerRepository;
//        Optional<Customer> customer = customerRepository.findCustomerByEmail("naman@scaler.com");
//
//        if (customer.isEmpty()) {
//            System.out.println("No customer matching the criteria");
//        }

    }

    // your db got leaked
    // 12345678 -> abcdef
    // f(1233454salt5677, )


    public boolean checkPassword(String password, PasswordEncoder passwordEncoder) {
        // this method checks whether the given password
        // matches the actual password
        String salt = this.hashedSaltedPassword.split(";")[1];
        // password-encoder is a dependency that we need
        String encoded = passwordEncoder.encode(password + salt) + salt;
        return encoded.equals(this.getHashedSaltedPassword());
    }

    public void addRole(Role role) {
        this.roles.add(role);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", hashedSaltedPassword='" + hashedSaltedPassword + '\'' +
                ", roles=" + roles +
                super.toString() +
                '}';
    }
}

// Fat models
// Anemic models -> Anemia - shortage of blood

// Composition over Inheritance
// Bridge / Composite / Databases
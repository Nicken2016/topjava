package ru.javawebinar.topjava.model;

import java.util.Collection;
import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

class User extends NamedEntity{
    private String email;
//    Length(min =5)
    private String password;
    private boolean enabled = true;
    private Date registered = new Date();
    private Set<Role> authorities;
    public User(){
    }

    public User(String name, String email, String password, Role role, Role... roles) {
        super(name);
        this.email = email;
        this.password = password;
        this.enabled = true;
        this.registered = registered;
        this.authorities = EnumSet.of(role, roles);
    }

    public boolean isEnabled(){return enabled;}
    public Collection<Role> getAuthorities(){ return authorities;}
    public String getPassword(){return password;}

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", enabled=" + enabled +
                ", registered=" + registered +
                '}';
    }
}

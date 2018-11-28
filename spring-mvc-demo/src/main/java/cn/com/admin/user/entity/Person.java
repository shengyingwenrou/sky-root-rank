package cn.com.admin.user.entity;

import cn.com.admin.user.service.PersonService;

/**
 * Created by sky.song on 2018/11/27.
 */
public class Person {

    private PersonService personService;

    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    public PersonService getPersonService(){
        return personService;
    }

    public static void main(String[] args) {

    }

}

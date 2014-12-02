package com.ggr.spring4.service;

import org.springframework.stereotype.Component;

import com.ggr.spring4.component.IPersonService;
import com.ggr.spring4.dto.Person;

@Component
public class PersonService implements IPersonService
{
    public Person getPersonDetail(Integer id)
    {
	Person p = new Person();
	p.setId(id);
	p.setLocation("Varanasi");
	p.setName("Ram");
	return p;
    }
}

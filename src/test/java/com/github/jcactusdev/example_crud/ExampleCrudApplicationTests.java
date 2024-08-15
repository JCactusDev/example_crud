package com.github.jcactusdev.example_crud;

import com.github.jcactusdev.example_crud.controller.OrganizationRestControllerTest;
import org.junit.platform.suite.api.ExcludePackages;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({OrganizationRestControllerTest.class})
class ExampleCrudApplicationTests {

}


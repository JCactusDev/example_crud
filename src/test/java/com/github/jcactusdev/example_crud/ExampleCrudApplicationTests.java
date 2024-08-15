package com.github.jcactusdev.example_crud;

import com.github.jcactusdev.example_crud.controller.ClientOrderRestControllerTest;
import com.github.jcactusdev.example_crud.controller.ClientRestControllerTest;
import com.github.jcactusdev.example_crud.controller.OrganizationRestControllerTest;
import com.github.jcactusdev.example_crud.controller.ProductRestControllerTest;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({OrganizationRestControllerTest.class, ClientRestControllerTest.class, ProductRestControllerTest.class, ClientOrderRestControllerTest.class})
class ExampleCrudApplicationTests {}


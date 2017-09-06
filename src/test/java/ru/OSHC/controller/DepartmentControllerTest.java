package ru.OSHC.controller;

import org.junit.Test;
import ru.OSHC.entity.Department;
import ru.OSHC.service.DepartmentService;

import java.io.StringWriter;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DepartmentControllerTest {
    final private static DepartmentService service = mock(DepartmentService.class);

    @Test
    public void getAll() throws Exception {
        final StringWriter stringWriter = new StringWriter();
    }

}
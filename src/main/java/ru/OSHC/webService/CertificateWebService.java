package ru.OSHC.webService;

import ru.OSHC.service.CertificateService;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.sql.SQLException;
import java.util.List;

@WebService(serviceName = "/certificates")
public class CertificateWebService {

    private CertificateService certificateService;

    @WebMethod(operationName = "getCerts")
    public List getCertificates() throws SQLException {
        return certificateService.getAll("getCertificateList");
    }

    public void setCertificateService(CertificateService certificateService) {
        this.certificateService = certificateService;
    }

    public CertificateService getCertificateService() {
        return certificateService;
    }
}

package com.materiel.gestion.apigestion.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.materiel.gestion.apigestion.model.entite.Client;
import com.materiel.gestion.apigestion.model.entite.Contact;
import com.materiel.gestion.apigestion.model.entite.Incident;
import com.materiel.gestion.apigestion.model.entite.Materiel;
import com.materiel.gestion.apigestion.service.IClientService;
import com.materiel.gestion.apigestion.service.IContactService;
import com.materiel.gestion.apigestion.service.IIncidentService;
import com.materiel.gestion.apigestion.service.IMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/clients", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ClientRestController {
    @Autowired
    private IClientService service;
    
    @Autowired
    private IContactService contactService;

    @Autowired
    private IMaterielService materielService;
    
    @Autowired
    private IIncidentService incidentService;
    
    @Autowired
    private ApplicationContext appContext;

    @GetMapping("/{id}")
    public Client getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<Client> getAll() {
        return service.getAll();
    }
    
    @PostMapping
    public ResponseEntity<Client> create(@RequestBody Client client) throws URISyntaxException {
    	Client c = service.create(client);
    	return ResponseEntity.created(new URI("api/v1/clients/" + c.getId())).body(c);
    }
    
    @PutMapping("/{id}")
    public Client edit(@RequestBody Client client, @PathVariable Long id) {
        client.setId(id);
        Client c = service.edit(client);
        return c;
    }
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id){
        Client c = new Client(id);
        service.delete(c);
    }

    /****** CONTACT ******/
    
    @GetMapping("/{id}/contacts")
    public List<Contact> getAllContacts(@PathVariable Long id) {
		return contactService.getByClient(id);
	}
    
    @GetMapping("/{id}/contacts/{idContact}")
    public Contact getAllContacts(@PathVariable Long id, @PathVariable Long idContact) {
        return contactService.getById(idContact, id);
    }
    
    @PostMapping("/{id}/contacts")
	public ResponseEntity<Contact> createContact(@RequestBody Contact contact, @PathVariable Long id) throws URISyntaxException {
    	// On ajoute l'id client au contact
    	contact.setClient(new Client(id));
    	
		Contact c = contactService.create(contact);
		return ResponseEntity.created(new URI("api/v1/contacts/" + c.getId())).body(c);
	}
    
    @PutMapping("/{id}/contacts/{idContact}")
    public Contact editContact(@RequestBody Contact contact, @PathVariable Long id, @PathVariable Long idContact) {
    	contact.setClient(new Client(id));
    	contact.setId(idContact);
    	
    	return contactService.edit(contact);
    }
    
    @DeleteMapping("/{id}/contacts/{idContact}")
    public void deleteContact(@PathVariable Long id, @PathVariable Long idContact) {
    	Client cl = new Client(id);
    	Contact c = new Contact(idContact);
    	c.setClient(cl);
    	
    	contactService.delete(c);
    }

    /****** MATERIEL ******/

    @GetMapping("/{id}/materiels")
    public List<Materiel> getAllMateriels(@PathVariable Long id) {
        return materielService.getByClient(id);
    }

    @GetMapping("/{id}/materiels/{idMateriel}")
    public Materiel getMaterielById(@PathVariable Long id, @PathVariable Long idMateriel) {
        return materielService.getById(idMateriel, id);
    }
    
    @GetMapping(value="/{id}/materiels/{idMateriel}/qrcode", produces=MediaType.IMAGE_PNG_VALUE)
    public byte[] getQrCode(@PathVariable Long id, @PathVariable Long idMateriel) {
    	return materielService.getQrCodeById(idMateriel, id);
    }

    @PostMapping("/{id}/materiels")
    public ResponseEntity<Materiel> create(@RequestBody Materiel materiel, @PathVariable Long id) throws URISyntaxException {
        // On ajoute l'id client au contact
        materiel.setClient(new Client(id));

        Materiel m = materielService.create(materiel);
        return ResponseEntity.created(new URI("api/v1/materiels/" + m.getId())).body(m);
    }
    
    @PutMapping("/{id}/materiels/{idMateriel}")
    public Materiel edit(@RequestBody Materiel materiel, @PathVariable Long id,  @PathVariable Long idMateriel){
        Client m1 = new Client(id);
        materiel.setClient(m1);
        materiel.setId(idMateriel);
        return materielService.edit(materiel);
    }
    
    @DeleteMapping("/{id}/materiels/{idMateriel}")
    public void deleteMateriel( @PathVariable Long id, @PathVariable Long idMateriel){
        Client ml = new Client(id);
        Materiel m = new Materiel(idMateriel);
        m.setClient(ml);
        materielService.delete(m);
    }
    
    /****** INCIDENTS ******/
    
    private List<Incident> getIncidentsInIntervalle(Long id, Date dateDebut, Date dateFin) {
    	dateDebut = dateDebut == null ? new Date(Long.MIN_VALUE) : dateDebut;
    	dateFin = dateFin == null ? new Date(Long.MAX_VALUE) : dateFin;
    	return incidentService.getByClientAndDates(id, dateDebut, dateFin);
    }
    
    @GetMapping(path = "/{id}/incidents")
    public List<Incident> getIncidentsInIntervalleJson(@PathVariable Long id, @RequestParam Date dateDebut, @RequestParam Date dateFin) {
    	return this.getIncidentsInIntervalle(id, dateDebut, dateFin);
    }
    
    @GetMapping(path = "/{id}/incidents", produces = MediaType.TEXT_HTML_VALUE)
    public String getIncidentsInIntervalleHtml(@PathVariable Long id, @RequestParam Date dateDebut, @RequestParam Date dateFin) {
    	List<Incident> incidents = this.getIncidentsInIntervalle(id, dateDebut, dateFin);
    	Client c = this.service.getById(id);
    	return this.toHtml(incidents, c, dateDebut, dateFin);
    }
    
    @GetMapping(path = "/{id}/incidents", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> getIncidentsInIntervallePdf(@PathVariable Long id, @RequestParam Date dateDebut, @RequestParam Date dateFin) throws Exception {
    	List<Incident> incidents = this.getIncidentsInIntervalle(id, dateDebut, dateFin);
    	Client c = this.service.getById(id);
    	String html = this.toHtml(incidents, c, dateDebut, dateFin);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
    	String filename = "incidents" + c.getNom() + "_" + sdf.format(dateDebut) + "_" + sdf.format(dateFin) + ".pdf";
    	
    	ByteArrayOutputStream pdfStream = new ByteArrayOutputStream();
    	ITextRenderer renderer = new ITextRenderer();
    	renderer.setDocumentFromString(html);
    	renderer.layout();
    	renderer.createPDF(pdfStream);
    	pdfStream.close();
    	
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        
        return ResponseEntity.ok().headers(headers).body(pdfStream.toByteArray());
    }
    
    private String toHtml(List<Incident> incidents, Client c, Date dateDebut, Date dateFin) {	
		String template = "<!DOCTYPE html><html lang=\"fr\"><head><meta charset=\"utf-8\"/><title>Export incident</title><style>h1{text-align:center}table{border-collapse:collapse;width:90%;margin-left:5%;text-align:center}table td,table th{border:1px solid black}</style></head><body><h1>Incident actifs<br/>{CLIENT} du {DEBUT} à {FIN}</h1><table><tr><th>Id</th><th>Libellé</th><th>Date création</th><th>Date clôture</th><th>Symptôme</th><th>Résolution</th></tr>{LIGNES}</table></body></html>";
		String td = "</td><td>";	
		StringBuilder lignes = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		for(Incident i : incidents) {
			lignes.append("<tr><td>").append(i.getId())
					.append(td).append(i.getLibelle())
					.append(td).append(i.getDateCreation() == null ? "-" : sdf.format(i.getDateCreation()))
					.append(td).append(i.getDateCloture() == null ? "-" : sdf.format(i.getDateCloture()))
					.append(td).append(i.getSymptome())
					.append(td).append(i.getResolution()).append("</td></tr>");
		}
		
		return template.replace("{LIGNES}", lignes.toString())
				.replace("{DEBUT}", sdf.format(dateDebut))
				.replace("{FIN}", sdf.format(dateFin))
				.replace("{CLIENT}", c.getNom());
	}
    
}

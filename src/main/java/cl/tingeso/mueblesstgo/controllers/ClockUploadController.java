package cl.tingeso.mueblesstgo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cl.tingeso.mueblesstgo.services.ClockUploadService;

@Controller
public class ClockUploadController {

    @Autowired
    private ClockUploadService upload;

    @GetMapping("/upload-clock")
    public String upload() {
        return "upload-clock";
    }

    @PostMapping("/save-clock")
    public String save(@RequestParam("file") MultipartFile file, RedirectAttributes ms) {
        upload.saveClock(file);
        ms.addFlashAttribute("mensaje", "Archivo guardado correctamente!!");
        return "redirect:/upload-clock";
    }
}

package nl.novi.techiteasy.Controllers;
import nl.novi.techiteasy.Exceptions.RecordNotFoundException;
import nl.novi.techiteasy.Modules.Television;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;


@RestController
public class TelevisionController {
        private ArrayList<Television> televisions;

        public TelevisionController() {
            televisions = new ArrayList<>();

            Television t = new Television();
            t.setName("Itelevision pro max");
            t.setBrand("Apple");
            t.setPrice (500);
            televisions.add(t);
        }

        @GetMapping("/televisions")
        public ResponseEntity<Object> getTelevisions() {
            return new ResponseEntity<>(televisions, HttpStatus.OK);
        }

        @GetMapping("/televisions/{id}")
        public ResponseEntity<Object> getTelevision(@PathVariable int id) {
            if (id >= 0 && id < televisions.size()) {
                return new ResponseEntity<>(televisions.get(id), HttpStatus.OK);
            } else {
                throw new RecordNotFoundException("Invalid-ID");
            }
        }

        @PostMapping("/televisions")
        public ResponseEntity<Television> createTelevision(@RequestBody Television television) {
            televisions.add(television);
            return new ResponseEntity<>(television, HttpStatus.CREATED);
        }

        @PutMapping("/televisions/{id}")
        public ResponseEntity<Television> updateTelevision(@PathVariable int id, @RequestBody Television t) {
            if (id >= 0 && id < televisions.size()) {
                televisions.set(id, t);
                return new ResponseEntity<>(t, HttpStatus.OK);
            }
            else {
                throw new RecordNotFoundException("Invalid-ID");
            }
        }
        @DeleteMapping("/televisions")
        public ResponseEntity<Object> deleteTelevision(@RequestBody String name){
            for (int i = 0; i < televisions.size(); i++){
                if (televisions.get(i).getName().equals(name)){
                    televisions.remove(i);
                    return new ResponseEntity<>("Item removed", HttpStatus.NO_CONTENT);
                }
            }
            throw new RecordNotFoundException("No television found");
        }

    }














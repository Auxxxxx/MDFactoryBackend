package md.factory.root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UsersController {

    @Autowired
    UsersRepository usersRepository;

    @GetMapping("/user")
    public List<User> index(){
        return usersRepository.findAll();
    }

    @GetMapping("/user/{id}")
    public User show(@PathVariable String id){
        int userId = Integer.parseInt(id);
        return usersRepository.findById(userId).get();
    }

    @PostMapping("/user/search")
    public List<User> search(@RequestBody Map<String, String> body){
        String searchTerm = body.get("text");
        return usersRepository.findByTitleContainingOrContentContaining(searchTerm, searchTerm);
    }

    @PostMapping("/user")
    public User create(@RequestBody Map<String, String> body){
        String name = body.get("name");
        String login = body.get("login");
        return usersRepository.save(new User(name, login));
    }

    @PutMapping("/user/{id}")
    public User update(@PathVariable String id, @RequestBody Map<String, String> body){
        int userId = Integer.parseInt(id);
        // getting blog
        User user = usersRepository.findById(userId).get();
        user.setName(body.get("name"));
        user.setLogin(body.get("login"));
        return usersRepository.save(user);
    }

    @DeleteMapping("user/{id}")
    public boolean delete(@PathVariable String id){
        int userId = Integer.parseInt(id);
        usersRepository.deleteById(userId);
        return true;
    }

}
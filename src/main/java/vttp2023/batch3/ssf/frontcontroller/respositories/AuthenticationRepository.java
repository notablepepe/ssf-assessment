package vttp2023.batch3.ssf.frontcontroller.respositories;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import vttp2023.batch3.ssf.frontcontroller.User;

@Repository
public class AuthenticationRepository {


	// TODO Task 5
	// Use this class to implement CRUD operations on Redis
	@Autowired 
    private RedisTemplate<String, Object> template;

    public void save(User user){
        template.opsForValue()
            .set(user.getUsername(), user.toJSON().toString());
    }

    public Optional<User> get(String username){
        String json = (String)template.opsForValue().get(username);
        if(null == json || json.trim().length() <= 0){
            return Optional.empty();
        }
        return Optional.of(User.create(json));
    }

	public void increaseFailedAttempts(User user) {
		user.setFailedAttempts(user.getFailedAttempts() + 1);
		save(user);
	}

	public void resetFailedAttempts(User user) {
		user.setFailedAttempts(0);
		save(user);
	}

	public void lock(User user) {
		user.setLocked(true);
		template.opsForValue()
            .set(user.getUsername(), user.toJSON().toString(), 30, TimeUnit.MINUTES);
	}

	public void unlockWhenTimeExpired() {

	}	

}

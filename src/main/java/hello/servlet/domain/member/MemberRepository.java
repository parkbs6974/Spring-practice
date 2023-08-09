package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 동시성 문제가 고려되어 있지 않음, 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려
 */
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();   //key =Long(id), value=Member
    private static long sequence = 0L; //아이디가 하나씩 증가

    private static final MemberRepository instance = new MemberRepository(); //singleton 작업을 위하여

    public static MemberRepository getInstance(){
        return instance;
    }

    private MemberRepository(){ //singleton으로 만들기 위하여 private을 이용하여 아무나 접근 못하게 만들어야 한다
    }

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member); //put의 역활이 무엇?
        return member;
    }

    public Member findById(Long id){
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values()); //store.values() -> store에 있는 모드 값들을 arrayList에 담아서 넘겨 준다
    }

    public void clearStore(){ //test 작업을 위하여 만듬
        store.clear();
    }
}

package com.cjf.es.repository;

import com.cjf.es.entity.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author chenjinfeng
 * @create 14:52 2019/5/16
 * @desc
 **/
@Repository
public interface UserRepository extends ElasticsearchRepository<User, String> {
//    JpaRepository<UserInfo, Long>, JpaSpecificationExecutor<UserInfo>
}
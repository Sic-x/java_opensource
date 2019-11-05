package com.xmh.aisell.repository;


import com.xmh.aisell.domain.SystemDictionaryDetail;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SystemDictionaryDetailRepository extends BaseRepository<SystemDictionaryDetail,Long> {

    @Query("select s from SystemDictionaryDetail s join s.systemDictionaryType t where t.sn =?1")
    List<SystemDictionaryDetail> findDetailsBySn(String sn);
}

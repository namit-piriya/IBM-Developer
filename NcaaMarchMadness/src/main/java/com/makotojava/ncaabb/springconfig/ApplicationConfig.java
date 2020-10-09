/*
 * Copyright 2017 Makoto Consulting Group, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.makotojava.ncaabb.springconfig;

import com.makotojava.ncaabb.dao.SeasonAnalyticsDao;
import com.makotojava.ncaabb.dao.SeasonAnalyticsJdbcDao;
import com.makotojava.ncaabb.dao.SeasonDataDao;
import com.makotojava.ncaabb.dao.SeasonDataFlatFileDao;
import com.makotojava.ncaabb.dao.TournamentAnalyticsDao;
import com.makotojava.ncaabb.dao.TournamentAnalyticsJdbcDao;
import com.makotojava.ncaabb.dao.TournamentParticipantDao;
import com.makotojava.ncaabb.dao.TournamentParticipantFlatFileDao;
import com.makotojava.ncaabb.dao.TournamentResultDao;
import com.makotojava.ncaabb.dao.TournamentResultFlatFileDao;
import com.makotojava.ncaabb.util.NetworkProperties;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Spring configuration class. Saves having to use XML files to configure Spring.
 * 
 * @author J Steven Perry
 *
 */
@Configuration
@ComponentScan(basePackages = {
    "com.makotojava.ncaabb.dao.*"
})
@EnableTransactionManagement
public class ApplicationConfig {

  @Bean(name = "dataSource")
  public DataSource getDataSource() {
    PGSimpleDataSource ret = new PGSimpleDataSource();
    //
    ret.setDatabaseName(NetworkProperties.getDatabaseName());
    ret.setPortNumber(5432);
    ret.setUser(NetworkProperties.getDatabaseUser());

    return ret;
  }

  @Bean(name = "seasonDataDao")
  public SeasonDataDao getSeasonDataDao() {
//    return new SeasonDataJdbcDao(getDataSource());
    return new SeasonDataFlatFileDao();
  }

  @Bean(name = "tournamentResultDao")
  public TournamentResultDao getTournamentResultDao() {
//    return new TournamentResultJdbcDao(getDataSource());
    return new TournamentResultFlatFileDao();
  }

  @Bean(name = "SeasonAnalyticsDao")
  public SeasonAnalyticsDao getSeasonAnalyticsDao() {
    return new SeasonAnalyticsJdbcDao(getDataSource());
  }

  @Bean(name = "tournamentAnalyticsDao")
  public TournamentAnalyticsDao getTournamentAnalyticsDao() {
    return new TournamentAnalyticsJdbcDao(getDataSource());
  }

  @Bean(name = "tournamentParticipantDao")
  public TournamentParticipantDao getTournamentParticipantDao() {
//    return new TournamentParticipantJdbcDao(getDataSource());
    return new TournamentParticipantFlatFileDao();
  }

}

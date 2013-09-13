package com.arthurspirke.cvcreator.dblayer.factories;

import com.arthurspirke.cvcreator.dblayer.daointerfaces.AddressDAO;
import com.arthurspirke.cvcreator.dblayer.daointerfaces.CertificateDAO;
import com.arthurspirke.cvcreator.dblayer.daointerfaces.CityDAO;
import com.arthurspirke.cvcreator.dblayer.daointerfaces.CountryDAO;
import com.arthurspirke.cvcreator.dblayer.daointerfaces.EducationDAO;
import com.arthurspirke.cvcreator.dblayer.daointerfaces.EmploymentHistoryDAO;
import com.arthurspirke.cvcreator.dblayer.daointerfaces.PersonLinksDAO;
import com.arthurspirke.cvcreator.dblayer.daointerfaces.PersonalDAO;
import com.arthurspirke.cvcreator.dblayer.daointerfaces.PersonalInfoDAO;
import com.arthurspirke.cvcreator.dblayer.daointerfaces.PersonalTemplatesDAO;
import com.arthurspirke.cvcreator.dblayer.daointerfaces.PhoneNumbersDAO;
import com.arthurspirke.cvcreator.dblayer.daointerfaces.ProjectDAO;
import com.arthurspirke.cvcreator.dblayer.daointerfaces.RegionDAO;
import com.arthurspirke.cvcreator.dblayer.daointerfaces.SkillsDAO;

public abstract class AbstractDAOFactory {
  public abstract AddressDAO getAddressDAO();
  public abstract CertificateDAO getCertificateDAO();
  public abstract EducationDAO getEducationDAO();
  public abstract EmploymentHistoryDAO getEmploymentHistoryDAO();
  public abstract PhoneNumbersDAO getPhoneNumbersDAO();
  public abstract PersonLinksDAO getPersonalLinksDAO();
  public abstract ProjectDAO getProjectDAO();
  public abstract SkillsDAO getSkillsDAO();
  public abstract PersonalInfoDAO getPersonalInfoDAO();
  public abstract PersonalTemplatesDAO getPersonalTemplatesDAO();
  public abstract PersonalDAO getPersonDAO();
  public abstract CountryDAO getCountryDAO();
  public abstract RegionDAO getRegionDAO();
  public abstract CityDAO getCityDAO();
}

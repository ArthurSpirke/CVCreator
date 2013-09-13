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

public class AbstractDAOFactorySupport extends AbstractDAOFactory{


	@Override
	public AddressDAO getAddressDAO() {
		throw new UnsupportedOperationException();
	}

	@Override
	public CertificateDAO getCertificateDAO() {
       throw new UnsupportedOperationException();
	}

	@Override
	public EducationDAO getEducationDAO() {
		throw new UnsupportedOperationException();
	}

	@Override
	public EmploymentHistoryDAO getEmploymentHistoryDAO() {
		throw new UnsupportedOperationException();
	}

	@Override
	public PhoneNumbersDAO getPhoneNumbersDAO() {
		throw new UnsupportedOperationException();
	}

	@Override
	public PersonLinksDAO getPersonalLinksDAO() {
		throw new UnsupportedOperationException();
	}

	@Override
	public ProjectDAO getProjectDAO() {
		throw new UnsupportedOperationException();
	}

	@Override
	public SkillsDAO getSkillsDAO() {
		throw new UnsupportedOperationException();
	}

	@Override
	public PersonalInfoDAO getPersonalInfoDAO() {
		throw new UnsupportedOperationException();
	}

	@Override
	public PersonalTemplatesDAO getPersonalTemplatesDAO() {
		throw new UnsupportedOperationException();
	}

	@Override
	public PersonalDAO getPersonDAO() {
		throw new UnsupportedOperationException();
	}

	@Override
	public CountryDAO getCountryDAO() {
		throw new UnsupportedOperationException();
	}

	@Override
	public RegionDAO getRegionDAO() {
		throw new UnsupportedOperationException();
	}

	@Override
	public CityDAO getCityDAO() {
		throw new UnsupportedOperationException();
	}


}

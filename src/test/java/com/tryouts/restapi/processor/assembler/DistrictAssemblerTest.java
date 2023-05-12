package com.tryouts.restapi.processor.assembler;

import com.tryouts.restapi.entity.District;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.LinkRelation;

import static org.junit.jupiter.api.Assertions.*;

class DistrictAssemblerTest {

	@Test
	void emptyDistrict() {
		DistrictAssembler districtAssembler= new DistrictAssembler();
		final EntityModel<District> districtEntityModel = districtAssembler.toModel(new District());
		Assertions.assertAll(
				()->Assertions.assertEquals(1, districtEntityModel.getLinks().stream().toList().size()),
				()->Assertions.assertEquals(Link.of("/district", "allDistricts").getHref(),districtEntityModel.getLinks().getLink("allDistricts").get().getHref()),
				()-> Assertions.assertEquals(Link.of("/district", "allDistricts").getRel(),districtEntityModel.getLinks().getLink("allDistricts").get().getRel()));
	}

	@Test
	void allLinks() {
		DistrictAssembler districtAssembler= new DistrictAssembler();
		final District district = new District();
		final long newID = 1L;
		district.setNewId(newID);
		final long oldId = 2L;
		district.setOldId(oldId);
		final long id = 4L;
		district.setId(id);
		final EntityModel<District> districtEntityModel = districtAssembler.toModel(district);
		final Link selfLink = Link.of("/district" + id, "self");
		final Link newLink = Link.of("/district/" + newID, "nextVersion");
		final Link oldLink = Link.of("/district/" + oldId, "lastVersion");
		final Link allLink = Link.of("/district", "allDistricts");
		Assertions.assertAll(
				()->Assertions.assertEquals(4, districtEntityModel.getLinks().stream().toList().size()),
				()->Assertions.assertEquals(allLink.getHref(),districtEntityModel.getLinks().getLink("allDistricts").get().getHref(),"allDistricts href failed"),
				()-> Assertions.assertEquals(allLink.getRel(),districtEntityModel.getLinks().getLink("allDistricts").get().getRel(),"allDistricts rel failed"),
				()->Assertions.assertEquals(oldLink.getHref(),districtEntityModel.getLinks().getLink("lastVersion").get().getHref(),"last href failed"),
				()-> Assertions.assertEquals(oldLink.getRel(),districtEntityModel.getLinks().getLink("lastVersion").get().getRel(),"last rel failed"),
				()->Assertions.assertEquals(newLink.getHref(),districtEntityModel.getLinks().getLink("nextVersion").get().getHref(),"next href failed"),
				()-> Assertions.assertEquals(newLink.getRel(),districtEntityModel.getLinks().getLink("nextVersion").get().getRel(),"next rel failed"),
				()->Assertions.assertEquals(selfLink.getHref(),districtEntityModel.getLinks().getLink("self").get().getHref(),"self href failed"),
				()-> Assertions.assertEquals(selfLink.getRel(),districtEntityModel.getLinks().getLink("self").get().getRel(),"self rel failed")

		);
	}

}
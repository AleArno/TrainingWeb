package example;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

@Path("people")
public class PersonResponse {

	@GET
	@Path("/filter")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getPeople(InputParameters in) {
		List<Person> people;
		PersonFinder pf = new PersonFinder(in);
		people = pf.find();
		Gson g = new Gson();
		return g.toJson(people);
	}

	@POST
	@Path("/input")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getPeoples(InputParameters in) {
		List<Person> people;
		PersonFinder pf = new PersonFinder(in);
		people = pf.find();
		Gson g = new Gson();
		return g.toJson(people);
	}

	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	public String insertPerson(Person p) {
		PersonInsert pi = new PersonInsert();
		return pi.insert(p);
	}

	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.TEXT_HTML)
	public String deletePerson(@PathParam("id") String id) {
		PersonDelete pd = new PersonDelete();
		return pd.delete(id);
	}

	@PUT
	@Path("/edit/{cf}")
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_JSON)
	public String editPerson(@PathParam("cf") String id, Person p) {
		PersonEdit pe = new PersonEdit();
		return pe.edit(id, p);

	}
}
import axios from 'axios';
import properties from '../properties.json'

const url = properties.serverURL;



class Service
{
	get()
	{
		return axios.get(url + '/').then(response => response.data);
	}
}



export let service = new Service();
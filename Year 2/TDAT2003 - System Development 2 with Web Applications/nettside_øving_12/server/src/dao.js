module.exports = class Dao
{
	constructor(pool)
	{
		this.pool = pool;

	}





	query(sql, params, callback)
	{
		this.pool.getConnection((err, connection) =>
		{
			if (err)
			{
				callback(500, {error: 'Feil ved oppkobling'});
			}
			else
			{
				connection.query(sql, params, (err, rows) =>
				{
					connection.release();
					if (err)
					{
						console.log(err);
						callback(500, {error: "error querying"});
					}
					else
					{
						callback(200, rows);
					}
				});
			}
		});
	}

}


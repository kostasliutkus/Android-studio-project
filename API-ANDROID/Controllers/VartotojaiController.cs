using API_ANDROID.Data;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace API_ANDROID.Controllers;

[ApiController]
public class VartotojaiController : ControllerBase
{
    private readonly AppDbContext _context;

    public VartotojaiController(AppDbContext context)
    {
        _context = context;
    }

    [HttpGet("api/Vartotojai")]
    public async Task<IActionResult> GetAllVartotojai()
    {
        var matavimai = await _context.Vartotojai.ToListAsync();
        return Ok(matavimai);
    }
}
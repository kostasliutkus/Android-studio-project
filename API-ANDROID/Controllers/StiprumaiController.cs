using API_ANDROID.Data;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace API_ANDROID.Controllers;

[ApiController]

public class StiprumaiController : ControllerBase
{
    private readonly AppDbContext _context;

    public StiprumaiController(AppDbContext context)
    {
        _context = context;
    }

    [HttpGet("api/Stiprumai")]
    public async Task<IActionResult> GetAllStiprumai()
    {
        var matavimai = await _context.Stiprumai.ToListAsync();
        return Ok(matavimai);
    }
    
}
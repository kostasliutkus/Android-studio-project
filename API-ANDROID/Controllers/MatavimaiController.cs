using API_ANDROID.Data;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace API_ANDROID.Controllers;

[ApiController]

public class MatavimaiController : ControllerBase
{
    private readonly AppDbContext _context;

    public MatavimaiController(AppDbContext context)
    {
        _context = context;
    }

    [HttpGet("api/Matavimai")]
    public async Task<IActionResult> GetAllMatavimai()
    {
        var matavimai = await _context.Matavimai.ToListAsync();
        return Ok(matavimai);
    }
}
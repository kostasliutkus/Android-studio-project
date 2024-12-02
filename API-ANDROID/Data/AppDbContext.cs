using API_ANDROID.Models;
using Microsoft.EntityFrameworkCore;

namespace API_ANDROID.Data;

public class AppDbContext :DbContext
{
    public DbSet<Matavimai> Matavimai { get; set; }
    public DbSet<Stiprumai> Stiprumai { get; set; }
    public DbSet<Vartotojai> Vartotojai { get; set; }

    public AppDbContext(DbContextOptions<AppDbContext> options) : base(options)
    {
    }
    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        base.OnModelCreating(modelBuilder);
        modelBuilder.Entity<Matavimai>().ToTable("matavimai");
        modelBuilder.Entity<Stiprumai>().ToTable("stiprumai");
        modelBuilder.Entity<Vartotojai>().ToTable("vartotojai");
    }
}